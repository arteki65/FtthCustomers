package pl.aptewicz.ftthcustomers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import pl.aptewicz.ftthcustomers.fragment.AddIssueFragment;
import pl.aptewicz.ftthcustomers.fragment.IssuesListFragment;
import pl.aptewicz.ftthcustomers.model.FtthCheckerUser;
import pl.aptewicz.ftthcustomers.model.FtthCustomer;
import pl.aptewicz.ftthcustomers.util.FragmentUtils;
import pl.aptewicz.ftthcustomers.util.ProgressUtils;

public class DashboardActivity extends AppCompatActivity implements AddIssueFragment.OnIssueAddedListener {

    private FtthCustomer ftthCustomer;

    private boolean addIssueClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initToolbar();
        setTitle(getString(R.string.issues_list_title));

        ftthCustomer = (FtthCustomer) getIntent().getSerializableExtra(FtthCustomer.FTTH_CUSTOMER);

        IssuesListFragment issuesListFragment = new IssuesListFragment();
        FragmentUtils.passSerializableArg(issuesListFragment, FtthCustomer.FTTH_CUSTOMER, ftthCustomer);
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, issuesListFragment)
                .commit();
    }

    private void initToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_dashboard_add_issue).setVisible(!addIssueClicked);
        addIssueClicked = false;
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dashboard_add_issue:
                AddIssueFragment addIssueFragment = new AddIssueFragment();
                FragmentUtils.passSerializableArg(addIssueFragment, FtthCustomer.FTTH_CUSTOMER, ftthCustomer);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, addIssueFragment).addToBackStack("addIssue").commit();
                addIssueClicked = true;
                invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        invalidateOptionsMenu();
    }

    @Override
    public void onIssueAdded(FtthCustomer ftthCustomer) {

        this.ftthCustomer = ftthCustomer;

        IssuesListFragment issuesListFragment = new IssuesListFragment();
        FragmentUtils.passSerializableArg(issuesListFragment, FtthCustomer.FTTH_CUSTOMER, ftthCustomer);
        getSupportFragmentManager().popBackStack("addIssue", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, issuesListFragment).commit();
        invalidateOptionsMenu();
    }
}
