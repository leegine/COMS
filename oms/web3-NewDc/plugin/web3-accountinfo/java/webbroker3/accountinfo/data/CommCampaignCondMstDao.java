head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignCondMstDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommCampaignCondMstDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CommCampaignCondMstRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CommCampaignCondMstPK 
 * @@see CommCampaignCondMstRow 
 */
public class CommCampaignCondMstDao extends DataAccessObject {


  /** 
   * ����{@@link CommCampaignCondMstDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CommCampaignCondMstRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CommCampaignCondMstRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CommCampaignCondMstDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CommCampaignCondMstDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CommCampaignCondMstRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCampaignCondMstRow )
                return new CommCampaignCondMstDao( (CommCampaignCondMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCampaignCondMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCampaignCondMstRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CommCampaignCondMstRow}�I�u�W�F�N�g 
    */
    protected CommCampaignCondMstDao( CommCampaignCondMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CommCampaignCondMstRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CommCampaignCondMstRow getCommCampaignCondMstRow() {
        return row;
    }


  /** 
   * �w���{@@link CommCampaignCondMstRow}�I�u�W�F�N�g����{@@link CommCampaignCondMstDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CommCampaignCondMstRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CommCampaignCondMstDao}�擾�̂��߂Ɏw���{@@link CommCampaignCondMstRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CommCampaignCondMstDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CommCampaignCondMstDao forRow( CommCampaignCondMstRow row ) throws java.lang.IllegalArgumentException {
        return (CommCampaignCondMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCampaignCondMstRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CommCampaignCondMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CommCampaignCondMstPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CommCampaignCondMstParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCampaignCondMstRow.TYPE );
    }


  /** 
   * {@@link CommCampaignCondMstRow}����ӂɓ��肷��{@@link CommCampaignCondMstPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CommCampaignCondMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CommCampaignCondMstParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CommCampaignCondMstPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CommCampaignCondMstPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new CommCampaignCondMstPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CommCampaignCondMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCampaignCondMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCampaignCondMstRow findRowByPk( long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( p_campaignId );
        return findRowByPk( pk );
    }


  /** 
   * �w���CommCampaignCondMstPK�I�u�W�F�N�g����{@@link CommCampaignCondMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CommCampaignCondMstPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCampaignCondMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCampaignCondMstRow findRowByPk( CommCampaignCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCampaignCondMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(CommCampaignCondMstRow)}���g�p���Ă��������B 
   */
    public static CommCampaignCondMstDao findDaoByPk( long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( p_campaignId );
        CommCampaignCondMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CommCampaignCondMstPK)}�����{@@link #forRow(CommCampaignCondMstRow)}���g�p���Ă��������B 
   */
    public static CommCampaignCondMstDao findDaoByPk( CommCampaignCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignCondMstRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link CommCampaignCondMstDao}�Ɋ֘A����{@@link CommCampaignCondMstRow}�̊O���L�[������{@@link CommCampaignProductMstRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link CommCampaignCondMstDao}�Ɋ֘A����{@@link CommCampaignCondMstRow}�̊O���L�[������{@@link CommCampaignProductMstRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchCommCampaignProductMstRowsByCampaignId() throws DataNetworkException, DataQueryException  {
        return CommCampaignProductMstDao.findRowsByCampaignId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchCommCampaignProductMstRowsByCampaignId()}�����{@@link #forRow(CommCampaignCondMstRow)}���g�p���Ă��������B 
   */
    public List fetchCommCampaignProductMstDaosByCampaignId() throws DataNetworkException, DataQueryException  {
        return CommCampaignProductMstDao.findDaosByCampaignId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchCommCampaignProductMstRowsByCampaignId()}�����{@@link #forRow(CommCampaignCondMstRow)}���g�p���Ă��������B 
   */
    public List fetchCommCampaignProductMstDaosCampaignId() throws DataNetworkException, DataQueryException  {
        return fetchCommCampaignProductMstDaosByCampaignId();
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
   * p_campaignId, and �ɂĎw��̒l�����ӂ�{@@link CommCampaignCondMstRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * 
   * @@return �����w���p_campaignId, and �̒l�ƈ�v����{@@link CommCampaignCondMstRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CommCampaignCondMstRow findRowByCampaignId( long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCampaignCondMstRow.TYPE,
            "campaign_id=?",
            null,
            new Object[] { new Long(p_campaignId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCampaignCondMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCampaignCondMstDao.findRowsByCampaignId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByCampaignId(long)}�����{@@link #forRow(CommCampaignCondMstRow)}���g�p���Ă��������B 
   */
    public static CommCampaignCondMstDao findDaoByCampaignId( long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCampaignId( p_campaignId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_registType, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignCondMstRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_registType �����Ώۂł���p_registType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_registType, and �̒l�ƈ�v����{@@link CommCampaignCondMstRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeRegistType( String p_institutionCode, String p_registType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignCondMstRow.TYPE,
            "institution_code=? and regist_type=?",
            null,
            new Object[] { p_institutionCode, p_registType } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeRegistType(String, String)}�����{@@link #forRow(CommCampaignCondMstRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeRegistType( String p_institutionCode, String p_registType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeRegistType( p_institutionCode, p_registType ) );
    }

}
@
