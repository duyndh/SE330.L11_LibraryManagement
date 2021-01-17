package data.DAOs;

import data.Models.MemberModel;

public class MemberDAO extends BaseDAO<MemberModel> {
    public MemberDAO() {
        super(MemberModel.class);
    }
}
