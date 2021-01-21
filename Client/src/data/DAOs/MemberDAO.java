package data.DAOs;

import UI.Models.DomainModels.MemberModel;

public class MemberDAO extends BaseDAO<MemberModel> {
    public MemberDAO() {
        super(MemberModel.class);
    }
}
