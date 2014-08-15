package com.tytanapps.game2048;

import java.io.File;
import java.io.IOException;

import com.tytanapps.game2048.R;
import com.tytanapps.game2048.R.id;
import com.tytanapps.game2048.R.layout;
import com.tytanapps.game2048.R.menu;
import com.tytanapps.game2048.R.string;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class StatsActivity extends Activity {
	
	Statistics gameStats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_settings) {
			Intent showSettings = new Intent(this, com.tytanapps.game2048.SettingsActivity.class);
			startActivity(showSettings);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onResume() {
		
		File gameStatsFile = new File(getFilesDir(), getString(R.string.file_game_stats));

		try {
			gameStats = (Statistics) Save.load(gameStatsFile);
		} catch (ClassNotFoundException e) {
			gameStats = new Statistics();
		} catch (IOException e) {
			gameStats = new Statistics();
		}
		TextView totalGames = (TextView) findViewById(R.id.total_games);
		TextView totalUndos = (TextView) findViewById(R.id.total_undos);
		TextView totalShuffles = (TextView) findViewById(R.id.total_shuffles);
		TextView totalMoves = (TextView) findViewById(R.id.total_moves);
		TextView highScore = (TextView) findViewById(R.id.high_score);
		TextView highestTile = (TextView) findViewById(R.id.highest_tile);
		TextView lowestScore = (TextView) findViewById(R.id.lowest_score);
		
		totalGames.setText(getString(R.string.total_games) + ": "
				+ gameStats.totalGamesPlayed);
		totalUndos.setText(getString(R.string.total_undos) + ": "
				+ gameStats.totalUndosUsed);
		totalShuffles.setText(getString(R.string.total_shuffles) + ": "
				+ gameStats.totalShufflesUsed);
		totalMoves.setText(getString(R.string.total_moves) + ": "
				+ gameStats.totalMoves);
		highScore.setText(getString(R.string.high_score) + ": "
				+ gameStats.highScore);
		highestTile.setText(getString(R.string.highest_tile) + ": "
				+ gameStats.highestTile);
		lowestScore.setText(getString(R.string.lowest_score) + ": "
				+ gameStats.lowScore);
		
		super.onResume();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_stats,
					container, false);
			return rootView;
		}
	}
}