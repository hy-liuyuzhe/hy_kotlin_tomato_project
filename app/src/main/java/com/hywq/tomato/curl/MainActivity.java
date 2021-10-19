package com.hywq.tomato.curl;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hywq.tomato.R;
import com.hywq.tomato.fragment.AnalyticeFragment;

public class MainActivity extends AppCompatActivity {

   private static final String TAG = "MainActivity";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
      if (viewPager != null) {
         viewPager.setAdapter(new TestPagerAdapter(getSupportFragmentManager()));
         viewPager.setPageTransformer(false, new PageCurlPageTransformer());
      }

   }

   public static class TestPagerAdapter extends FragmentPagerAdapter {

      TestPagerAdapter(FragmentManager fm) {
         super(fm);
      }

      @Override
      public Fragment getItem(int position) {
         if (position == 4) {
            return new AnalyticeFragment();
         }
         return TestFragment.getInstance(position + 1);
      }

      @Override
      public int getCount() {
         return 6;
      }
   }

   public static class TestFragment extends Fragment {

      private int mPage;

      public static TestFragment getInstance(int page) {
         TestFragment testFragment = new TestFragment();
         Bundle args = new Bundle();
         args.putInt("page", page);
         testFragment.setArguments(args);
         return testFragment;
      }

      @Override
      public void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         mPage = getArguments().getInt("page");
      }

      @Nullable
      @Override
      public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_test, container, false);
         TextView textView = (TextView) view.findViewById(R.id.text);
         textView.setText(getSampleText());
         view.setTag(R.id.viewpager, mPage);
         return view;
      }

      private String getSampleText() {

         String str = "Page " + mPage + " ";
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < 300; i++) {
            sb.append(str);
         }
         return sb.toString();
      }
   }

   public static class PageCurlPageTransformer implements ViewPager.PageTransformer {

      @Override
      public void transformPage(View page, float position) {

         Log.d(TAG, "transformPage, position = " + position + ", page = " + page.getTag(R.id.viewpager));
         if (page instanceof PageCurl) {
            if (position > -1.0F && position < 1.0F) {
               // hold the page steady and let the views do the work
               page.setTranslationX(-position * page.getWidth());
            } else {
               page.setTranslationX(0.0F);
            }
            if (position <= 1.0F && position >= -1.0F) {
               ((PageCurl) page).setCurlFactor(position);
            }
         }
      }
   }
}
