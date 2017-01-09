package pl.aptewicz.ftthcustomers.network;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pl.aptewicz.ftthcustomers.model.FtthCustomer;

public class FtthCheckerRestApiRequest extends JsonObjectRequest {

	private final FtthCustomer ftthCustomer;

	public FtthCheckerRestApiRequest(int method, String url, JSONObject jsonRequest,
			Response.Listener<JSONObject> listener, Response.ErrorListener errorListener,
			FtthCustomer ftthCustomer) {
		super(method, url, jsonRequest, listener, errorListener);
		this.ftthCustomer = ftthCustomer;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = new HashMap<>();
		headers.putAll(super.getHeaders());
		headers.put("Authorization", "Basic " + Base64.encodeToString(
				(ftthCustomer.getUsername() + ":" + ftthCustomer.getPassword()).getBytes(),
				Base64.DEFAULT));
		return headers;
	}
}
