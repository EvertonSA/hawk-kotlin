package `in`.arakaki.hawk.repository

import `in`.arakaki.hawk.model.Breed
import org.springframework.data.mongodb.repository.MongoRepository

interface BreedRepository : MongoRepository<Breed, String> {

}