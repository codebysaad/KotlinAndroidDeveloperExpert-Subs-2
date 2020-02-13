package xyz.webflutter.kadefootballlanguage.view

import xyz.webflutter.kadefootballlanguage.model.ModelLeague

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLeague(data: List<ModelLeague>)
}