package ir.elegam.school.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter_Consult extends FragmentPagerAdapter{

	private Context context;
	private Typeface San;
	private final List<Fragment> mFragmentList = new ArrayList<>();
	private final List<String> mFragmentTitleList = new ArrayList<>();
	
	public PagerAdapter_Consult(FragmentManager fm, Context context, Typeface San) {
		super(fm);
		this.context = context;
		this.San = San;
	}// end Cunstractor()

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}// end getItem()
	
	@Override
	public int getCount() {
		return mFragmentList.size();
	}// end getCount()

	public void addFragment(Fragment fragment, String title) {
		mFragmentList.add(fragment);
		mFragmentTitleList.add(title);
	}// addFragment()

	@Override
	public CharSequence getPageTitle(int position) {
		return mFragmentTitleList.get(position);
	}// end getPageTitle()
	
}// end class
