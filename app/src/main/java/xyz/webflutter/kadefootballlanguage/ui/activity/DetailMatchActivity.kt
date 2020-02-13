package xyz.webflutter.kadefootballlanguage.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.model.ModelMatch
import xyz.webflutter.kadefootballlanguage.model.ModelTeams
import xyz.webflutter.kadefootballlanguage.presenter.MatchPresenter
import xyz.webflutter.kadefootballlanguage.utils.convertDate
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.DetailView

class DetailMatchActivity : AppCompatActivity(), DetailView, (ModelMatch) -> Unit {
    companion object {
        const val EXTRA_ID_EVENT = "EVENT_ID"
        const val EXTRA_STR_EVENT = "EVENT_STR"
        const val EXTRA_ID_HOME_TEAM = "HOME_ID"
        const val EXTRA_ID_AWAY_TEAM = "AWAY_ID"
    }

    private var detailMatch: MutableList<ModelMatch> = mutableListOf()
    private lateinit var presenter: MatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        val getId = intent.getStringExtra(EXTRA_ID_EVENT)
        val getName = intent.getStringExtra(EXTRA_STR_EVENT)
        val idHomeTeam = intent.getStringExtra(EXTRA_ID_HOME_TEAM)
        val idAwayTeam = intent.getStringExtra(EXTRA_ID_AWAY_TEAM)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        supportActionBar?.apply {
            title = getName
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
            presenter.getDetailMatch(getId!!)
        presenter.getShowTeamBadgeHome(idHomeTeam!!)
        presenter.getShowTeamBadgeAway(idAwayTeam!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                val toast = Toast.makeText(this, "Is Favorited", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showTeamBadgeHome(data: List<ModelTeams>) {
        Picasso.get().load(data[0].teamBadge).into(badge_home_detail)
        badge_home_detail.visible()
    }

    override fun showTeamBadgeAway(data: List<ModelTeams>) {
        Picasso.get().load(data[0].teamBadge).into(badge_away_detail)
        badge_away_detail.visible()
    }

    override fun showLoading() {
        progress_bar_detail_match.visible()
    }

    override fun hideLoading() {
        progress_bar_detail_match.invisible()
    }

    override fun showDetailMatch(data: List<ModelMatch>) {
        detailMatch.clear()
        detailMatch.addAll(data)
        str_match_date_detail.text = convertDate(data[0].dateEvent?:"", data[0].strTime?:"")
        str_team_home_detail.text = data[0].strHomeTeam
        str_team_away_detail.text = data[0].strAwayTeam
        str_home_goal_detail.text = data[0].intHomeScore
        str_away_goal_detail.text = data[0].intAwayScore
        str_away_shot_detail.text = data[0].intAwayShots
        str_home_shot_detail.text = data[0].intHomeShots
        str_home_yellow_card.text = data[0].strHomeYellowCards
        str_away_yellow_card.text = data[0].strAwayYellowCards
        str_home_red_card.text = data[0].strHomeRedCards
        str_away_red_card.text = data[0].strAwayRedCards
        str_home_formation.text = data[0].strHomeFormation
        str_away_formation.text = data[0].strAwayFormation
        str_home_keeper.text = data[0].strHomeLineupGoalkeeper
        str_away_keeper.text = data[0].strAwayLineupGoalkeeper
        str_home_deffense.text = data[0].strHomeLineupDefense
        str_away_deffense.text = data[0].strAwayLineupDefense
        str_home_midfield.text = data[0].strHomeLineupMidfield
        str_away_midfield.text = data[0].strAwayLineupMidfield
        str_home_forward.text = data[0].strHomeLineupForward
        str_away_forward.text = data[0].strAwayLineupForward
        str_home_subtitutes.text = data[0].strHomeLineupSubstitutes
        str_away_subtitutes.text = data[0].strAwayLineupSubstitutes
    }

    override fun invoke(p1: ModelMatch) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
