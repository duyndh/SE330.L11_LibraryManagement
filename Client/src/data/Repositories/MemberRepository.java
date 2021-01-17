package data.Repositories;

import data.DAOs.MemberDAO;
import data.Models.MemberModel;

public class MemberRepository extends BaseRepository<MemberModel> {
    public MemberRepository() {
        super(new MemberDAO());
    }
}
