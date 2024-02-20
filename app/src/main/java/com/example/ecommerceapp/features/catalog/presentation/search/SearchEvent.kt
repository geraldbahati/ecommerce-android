package com.example.ecommerceapp.features.catalog.presentation.search

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
}