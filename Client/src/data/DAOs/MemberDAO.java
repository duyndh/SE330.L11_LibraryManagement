package data.DAOs;

import UI.Models.MemberModel;

public class MemberDAO extends BaseDAO<MemberModel> {
    public MemberDAO() {
        super(MemberModel.class);
    }
}
