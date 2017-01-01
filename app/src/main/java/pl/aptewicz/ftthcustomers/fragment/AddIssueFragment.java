package pl.aptewicz.ftthcustomers.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import pl.aptewicz.ftthcustomers.R;
import pl.aptewicz.ftthcustomers.model.FtthCustomer;
import pl.aptewicz.ftthcustomers.model.FtthIssue;
import pl.aptewicz.ftthcustomers.network.FtthCheckerRestApiRequest;
import pl.aptewicz.ftthcustomers.network.RequestQueueSingleton;
import pl.aptewicz.ftthcustomers.util.ProgressUtils;
import pl.aptewicz.ftthcustomers.util.SharedPreferencesUtils;

public class AddIssueFragment extends Fragment {

	private FtthCustomer ftthCustomer;

	private EditText issueDescription;

	private View addIssueForm;

	private View progressView;

	private OnIssueAddedListener onIssueAddedListener;

	private RequestQueueSingleton requestQueueSingleton;

	public interface OnIssueAddedListener {

		void onIssueAdded(FtthCustomer ftthCustomer);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		onIssueAddedListener = (OnIssueAddedListener) context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable
					ViewGroup container,
			@Nullable
					Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.add_issue_fragment, container, false);

		ftthCustomer = (FtthCustomer) getArguments().getSerializable(FtthCustomer.FTTH_CUSTOMER);

		Button addIssueButton = (Button) rootView.findViewById(R.id.add_issue_button);
		addIssueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				addIssue();
			}
		});

		issueDescription = (EditText) rootView.findViewById(R.id.issue_description);
		addIssueForm = rootView.findViewById(R.id.add_issue_form);
		progressView = rootView.findViewById(R.id.add_issue_progress);

		requestQueueSingleton = RequestQueueSingleton.getInstance(getActivity());

		return rootView;
	}

	public void addIssue() {
		InputMethodManager inputManager = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		View v = getActivity().getCurrentFocus();
		if (v != null) {
			inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
		ProgressUtils.showProgress(true, getContext(), addIssueForm, progressView);

		FtthIssue ftthIssue = new FtthIssue();
		ftthIssue.setDescription(issueDescription.getText().toString());

		if (SharedPreferencesUtils.isDummyLocation(getContext())) {
			ftthIssue.setLatitude(52.220259);
			ftthIssue.setLongitude(21.011758);
		}

		try {
			FtthCheckerRestApiRequest addIssueRequest = new FtthCheckerRestApiRequest(
					Request.Method.PUT,
					SharedPreferencesUtils.getServerHttpAddressWithContext(getContext())
							+ "/ftthIssue", new JSONObject(new Gson().toJson(ftthIssue)),
					new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							Toast.makeText(AddIssueFragment.this.getContext(),
									"Zgłoszenie utworzone", Toast.LENGTH_SHORT).show();
							FtthIssue ftthIssueFromResponse = new Gson()
									.fromJson(response.toString(), FtthIssue.class);
							ftthCustomer.getFtthIssues().add(ftthIssueFromResponse);
							ProgressUtils
									.showProgress(false, getContext(), addIssueForm, progressView);
							onIssueAddedListener.onIssueAdded(ftthCustomer);
						}
					}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Toast.makeText(AddIssueFragment.this.getContext(),
							"Wystąpił błąd podczas tworzenia zgłoszenia", Toast.LENGTH_SHORT)
							.show();
					ProgressUtils.showProgress(false, getContext(), addIssueForm, progressView);
				}
			}, ftthCustomer);

			requestQueueSingleton.addToRequestQueue(addIssueRequest);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
