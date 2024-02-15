package danielproj.ver2.Repositorys;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import danielproj.ver2.objects.User;



@Repository
public interface UserRepository extends MongoRepository<User, String>
{
    public User findByUsername(String Username);
}