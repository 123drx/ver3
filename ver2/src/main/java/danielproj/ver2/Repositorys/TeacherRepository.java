package danielproj.ver2.Repositorys;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import danielproj.ver2.objects.Teacher;



@Repository
public interface TeacherRepository extends MongoRepository<Teacher,ObjectId>{
    
    public Teacher findByName(String Name);
}

    

