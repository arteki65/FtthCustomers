package pl.aptewicz.ftthcustomers.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pl.aptewicz.ftthcustomers.R;
import pl.aptewicz.ftthcustomers.model.FtthCustomer;
import pl.aptewicz.ftthcustomers.util.ListViewUtils;


public class IssuesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.issues_list_fragment, container, false);

        getActivity().setTitle(getString(R.string.issues_list_title));

        FtthCustomer ftthCustomer = (FtthCustomer) getArguments().getSerializable(FtthCustomer.FTTH_CUSTOMER);

        if(ftthCustomer != null) {
            ListView issuesList = (ListView) rootView.findViewById(R.id.issues_list);
            issuesList.setAdapter(new ArrayAdapter<>(getActivity(), ListViewUtils.getListItemLayoutId(),
                    ftthCustomer.getFtthIssues().toArray()));
            issuesList.setEmptyView(rootView.findViewById(R.id.empty_issues_list_view));
        }

        return rootView;
    }
}
