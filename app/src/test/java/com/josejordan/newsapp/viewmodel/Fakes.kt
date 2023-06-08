package com.josejordan.newsapp.viewmodel

import com.josejordan.newsapp.data.News
import com.josejordan.newsapp.data.Source

val fakeSource: Source = Source("0", "name")
val fakeCountry = "us"
val fakeApiKey = "123456"
val fakeStatus = "ok"
val fakeTotalResult = 10

val fakeNews = listOf(
    News(
        fakeSource,
        "author",
        "title",
        "description",
        "url",
        "urltoImage",
        "publishedAt",
        "content"
    ),
    News(
        fakeSource,
        "author1",
        "title1",
        "description1",
        "url1",
        "urltoImage1",
        "publishedAt1",
        "content1"
    ),
    News(
        fakeSource,
        "author2",
        "title2",
        "description2",
        "url2",
        "urltoImage2",
        "publishedAt2",
        "content2"
    ),
    News(
        fakeSource,
        "author3",
        "title3",
        "description3",
        "url3",
        "urltoImage3",
        "publishedAt3",
        "content3"
    ),
)

