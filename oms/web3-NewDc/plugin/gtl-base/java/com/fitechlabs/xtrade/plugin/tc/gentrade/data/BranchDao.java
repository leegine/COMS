head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BranchDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link BranchDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BranchRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchPK 
 * @@see BranchRow 
 */
public class BranchDao extends DataAccessObject {


  /** 
   * ����{@@link BranchDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BranchRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BranchRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BranchDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BranchDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BranchRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BranchRow )
                return new BranchDao( (BranchRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BranchRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BranchRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BranchRow}�I�u�W�F�N�g 
    */
    protected BranchDao( BranchRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BranchRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BranchRow getBranchRow() {
        return row;
    }


  /** 
   * �w���{@@link BranchRow}�I�u�W�F�N�g����{@@link BranchDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BranchRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BranchDao}�擾�̂��߂Ɏw���{@@link BranchRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BranchDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BranchDao forRow( BranchRow row ) throws java.lang.IllegalArgumentException {
        return (BranchDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BranchRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BranchRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BranchPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BranchParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BranchRow.TYPE );
    }


  /** 
   * {@@link BranchRow}����ӂɓ��肷��{@@link BranchPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BranchRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BranchParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BranchPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BranchPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BranchPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BranchRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BranchRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BranchRow findRowByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchPK pk = new BranchPK( p_branchId );
        return findRowByPk( pk );
    }


  /** 
   * �w���BranchPK�I�u�W�F�N�g����{@@link BranchRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BranchPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BranchRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BranchRow findRowByPk( BranchPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BranchRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static BranchDao findDaoByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchPK pk = new BranchPK( p_branchId );
        BranchRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BranchPK)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static BranchDao findDaoByPk( BranchPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BranchDao}�ɕR�t��{@@link BranchRow}���ŊO���L�[�̊֌W������{@@link InstitutionRow}���������܂��B 
   * 
   * @@return {@@link BranchDao}�ƊO���L�[�̊֌W�ɂ���{@@link InstitutionRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public InstitutionRow fetchInstitutionRowViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        Row row = InstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof InstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (InstitutionRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchInstitutionRowViaInstitutionId()}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link BranchDao}�Ɋ֘A����{@@link BranchRow}�̊O���L�[������{@@link TraderRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link BranchDao}�Ɋ֘A����{@@link BranchRow}�̊O���L�[������{@@link TraderRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchTraderRowsByBranchId() throws DataNetworkException, DataQueryException  {
        return TraderDao.findRowsByBranchId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTraderRowsByBranchId()}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public List fetchTraderDaosByBranchId() throws DataNetworkException, DataQueryException  {
        return TraderDao.findDaosByBranchId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchTraderRowsByBranchId()}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public List fetchTraderDaosBranchId() throws DataNetworkException, DataQueryException  {
        return fetchTraderDaosByBranchId();
    }


  /** 
   * ����{@@link BranchDao}�Ɋ֘A����{@@link BranchRow}�̊O���L�[������{@@link MainAccountRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link BranchDao}�Ɋ֘A����{@@link BranchRow}�̊O���L�[������{@@link MainAccountRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchMainAccountRowsByBranchId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findRowsByBranchId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowsByBranchId()}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public List fetchMainAccountDaosByBranchId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findDaosByBranchId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowsByBranchId()}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public List fetchMainAccountDaosBranchId() throws DataNetworkException, DataQueryException  {
        return fetchMainAccountDaosByBranchId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Institution
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByInstitutionId(InstitutionRow)}���g�p���Ă��������B 
   */
    public static List findRowsByInstitutionId( InstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( dao.getInstitutionRow() );
    }


  /** 
   * {@@link InstitutionRow}�ƊO���L�[�̊֌W�ɂ���{@@link BranchRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link InstitutionRow}�I�u�W�F�N�g 
   * @@return �w���{@@link InstitutionRow}�ɊO���L�[������{@@link BranchRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}�ƊO���L�[�̊֌W�ɂ���{@@link BranchRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link InstitutionPK}�I�u�W�F�N�g 
   * @@return {@@link InstitutionPK}�ƊO���L�[����v����l������{@@link BranchRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BranchRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BranchRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BranchRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByInstitutionId(InstitutionRow)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(InstitutionRow)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(InstitutionPK)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(long)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_branchId, and �ɂĎw��̒l�����ӂ�{@@link BranchRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �����w���p_branchId, and �̒l�ƈ�v����{@@link BranchRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BranchRow findRowByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BranchRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BranchRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BranchDao.findRowsByBranchId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBranchId(long)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static BranchDao findDaoByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchId( p_branchId ) );
    }


  /** 
   * p_institutionId, p_branchCode, and �ɂĎw��̒l�����ӂ�{@@link BranchRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionId, p_branchCode, and �̒l�ƈ�v����{@@link BranchRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BranchRow findRowByInstitutionIdBranchCode( long p_institutionId, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BranchRow.TYPE,
            "institution_id=? and branch_code=?",
            null,
            new Object[] { new Long(p_institutionId), p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BranchRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BranchDao.findRowsByInstitutionIdBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionIdBranchCode(long, String)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static BranchDao findDaoByInstitutionIdBranchCode( long p_institutionId, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionIdBranchCode( p_institutionId, p_branchCode ) );
    }


  /** 
   * p_institutionCode, p_branchCode, and �ɂĎw��̒l�����ӂ�{@@link BranchRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, and �̒l�ƈ�v����{@@link BranchRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BranchRow findRowByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BranchRow.TYPE,
            "institution_code=? and branch_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BranchRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BranchDao.findRowsByInstitutionCodeBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCode(String, String)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static BranchDao findDaoByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCode( p_institutionCode, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_branchId, p_institutionCode, p_branchCode, and �ɂĎw��̒l�Ɉ�v����{@@link BranchRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_branchId, p_institutionCode, p_branchCode, and �̒l�ƈ�v����{@@link BranchRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchIdInstitutionCodeBranchCode( long p_branchId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BranchRow.TYPE,
            "branch_id=? and institution_code=? and branch_code=?",
            null,
            new Object[] { new Long(p_branchId), p_institutionCode, p_branchCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchIdInstitutionCodeBranchCode(long, String, String)}�����{@@link #forRow(BranchRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchIdInstitutionCodeBranchCode( long p_branchId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByBranchIdInstitutionCodeBranchCode( p_branchId, p_institutionCode, p_branchCode ) );
    }

}
@
