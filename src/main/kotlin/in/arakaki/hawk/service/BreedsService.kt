package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.model.Breed
import `in`.arakaki.hawk.repository.BreedRepository
import com.beust.klaxon.Klaxon
import okhttp3.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException

const val THE_CAT_BASE_URL = "https://api.thecatapi.com"
@Service("BreedsService")
class BreedsService {

    @Autowired
    lateinit var breedRepository: BreedRepository

    private var client = OkHttpClient()

    fun getAll(): List<Breed> {
        return breedRepository.findAll()
    }

    fun removeAll() {
        return breedRepository.deleteAll()
    }

    fun fetchAndSaveBreeds(): Iterable<Breed>? {
        val payload = fetchBreeds()
        if (payload != null) {
            payload.forEach{ u ->
                breedRepository.save(u)
            }
        }
        return payload
    }

    private fun fetchBreeds(): Iterable<Breed>?{
        val request = Request.Builder()
                .url("$THE_CAT_BASE_URL/v1/breeds")
                .build()
        val call = client.newCall(request)
        return  Klaxon().parseArray<Breed>(call.execute().body().string())
    }
}