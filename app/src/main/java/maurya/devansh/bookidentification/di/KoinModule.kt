package maurya.devansh.bookidentification.di

import maurya.devansh.bookidentification.screens.bookinfo.BookInfoRepository
import org.koin.dsl.module.module

/**
 * Created by Devansh on 28/4/19.
 */

val bookInfoRepositoryModule = module {
    single { BookInfoRepository(get()) }
}