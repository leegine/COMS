head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignProductMstDao.java;


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
 * {@@link CommCampaignProductMstDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CommCampaignProductMstRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CommCampaignProductMstPK 
 * @@see CommCampaignProductMstRow 
 */
public class CommCampaignProductMstDao extends DataAccessObject {


  /** 
   * ����{@@link CommCampaignProductMstDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CommCampaignProductMstRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CommCampaignProductMstRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CommCampaignProductMstDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CommCampaignProductMstDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CommCampaignProductMstRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCampaignProductMstRow )
                return new CommCampaignProductMstDao( (CommCampaignProductMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCampaignProductMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCampaignProductMstRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CommCampaignProductMstRow}�I�u�W�F�N�g 
    */
    protected CommCampaignProductMstDao( CommCampaignProductMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CommCampaignProductMstRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CommCampaignProductMstRow getCommCampaignProductMstRow() {
        return row;
    }


  /** 
   * �w���{@@link CommCampaignProductMstRow}�I�u�W�F�N�g����{@@link CommCampaignProductMstDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CommCampaignProductMstRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CommCampaignProductMstDao}�擾�̂��߂Ɏw���{@@link CommCampaignProductMstRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CommCampaignProductMstDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CommCampaignProductMstDao forRow( CommCampaignProductMstRow row ) throws java.lang.IllegalArgumentException {
        return (CommCampaignProductMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCampaignProductMstRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CommCampaignProductMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CommCampaignProductMstPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CommCampaignProductMstParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCampaignProductMstRow.TYPE );
    }


  /** 
   * {@@link CommCampaignProductMstRow}����ӂɓ��肷��{@@link CommCampaignProductMstPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CommCampaignProductMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CommCampaignProductMstParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CommCampaignProductMstPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CommCampaignProductMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CommCampaignProductMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCampaignProductMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCampaignProductMstRow findRowByPk( long p_campaignId, String p_commProductCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignProductMstPK pk = new CommCampaignProductMstPK( p_campaignId, p_commProductCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���CommCampaignProductMstPK�I�u�W�F�N�g����{@@link CommCampaignProductMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CommCampaignProductMstPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommCampaignProductMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommCampaignProductMstRow findRowByPk( CommCampaignProductMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCampaignProductMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static CommCampaignProductMstDao findDaoByPk( long p_campaignId, String p_commProductCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignProductMstPK pk = new CommCampaignProductMstPK( p_campaignId, p_commProductCode );
        CommCampaignProductMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CommCampaignProductMstPK)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static CommCampaignProductMstDao findDaoByPk( CommCampaignProductMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignProductMstRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link CommCampaignProductMstDao}�ɕR�t��{@@link CommCampaignProductMstRow}���ŊO���L�[�̊֌W������{@@link CommCampaignCondMstRow}���������܂��B 
   * 
   * @@return {@@link CommCampaignProductMstDao}�ƊO���L�[�̊֌W�ɂ���{@@link CommCampaignCondMstRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public CommCampaignCondMstRow fetchCommCampaignCondMstRowViaCampaignId() throws DataNetworkException, DataFindException, DataQueryException  {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( row.getCampaignId() );
        Row row = CommCampaignCondMstDao.findRowByPk( pk );
        if ( row != null && !(row instanceof CommCampaignCondMstRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (CommCampaignCondMstRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchCommCampaignCondMstRowViaCampaignId()}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public CommCampaignCondMstDao fetchCommCampaignCondMstDaoViaCampaignId() throws DataNetworkException, DataFindException, DataQueryException  {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( row.getCampaignId() );
        DataAccessObject dao = CommCampaignCondMstDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof CommCampaignCondMstDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (CommCampaignCondMstDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for CommCampaignCondMst
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByCampaignId(CommCampaignCondMstRow)}���g�p���Ă��������B 
   */
    public static List findRowsByCampaignId( CommCampaignCondMstDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByCampaignId( dao.getCommCampaignCondMstRow() );
    }


  /** 
   * {@@link CommCampaignCondMstRow}�ƊO���L�[�̊֌W�ɂ���{@@link CommCampaignProductMstRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link CommCampaignCondMstRow}�I�u�W�F�N�g 
   * @@return �w���{@@link CommCampaignCondMstRow}�ɊO���L�[������{@@link CommCampaignProductMstRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCampaignId( CommCampaignCondMstRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByCampaignId( row.getCampaignId() );
    }


  /** 
   * {@@link CommCampaignCondMstPK}�ƊO���L�[�̊֌W�ɂ���{@@link CommCampaignProductMstRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link CommCampaignCondMstPK}�I�u�W�F�N�g 
   * @@return {@@link CommCampaignCondMstPK}�ƊO���L�[����v����l������{@@link CommCampaignProductMstRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCampaignId( CommCampaignCondMstPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByCampaignId( pk.campaign_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link CommCampaignProductMstRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link CommCampaignProductMstRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCampaignId( long p_campaignId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignProductMstRow.TYPE,
            "campaign_id=?",
            null,
            new Object[] { new Long(p_campaignId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for CommCampaignCondMst
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByCampaignId(CommCampaignCondMstRow)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCampaignId( CommCampaignCondMstDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCampaignId(CommCampaignCondMstRow)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCampaignId( CommCampaignCondMstRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCampaignId(CommCampaignCondMstPK)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCampaignId( CommCampaignCondMstPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( pk.campaign_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCampaignId(long)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCampaignId( long p_campaignId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( p_campaignId ) );
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
   * p_campaignId, p_commProductCode, and �ɂĎw��̒l�����ӂ�{@@link CommCampaignProductMstRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_campaignId �����Ώۂł���p_campaignId�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_campaignId, p_commProductCode, and �̒l�ƈ�v����{@@link CommCampaignProductMstRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CommCampaignProductMstRow findRowByCampaignIdCommProductCode( long p_campaignId, String p_commProductCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCampaignProductMstRow.TYPE,
            "campaign_id=? and comm_product_code=?",
            null,
            new Object[] { new Long(p_campaignId), p_commProductCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCampaignProductMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCampaignProductMstDao.findRowsByCampaignIdCommProductCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByCampaignIdCommProductCode(long, String)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static CommCampaignProductMstDao findDaoByCampaignIdCommProductCode( long p_campaignId, String p_commProductCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCampaignIdCommProductCode( p_campaignId, p_commProductCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_commProductCode, and �ɂĎw��̒l�Ɉ�v����{@@link CommCampaignProductMstRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_commProductCode, and �̒l�ƈ�v����{@@link CommCampaignProductMstRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCommProductCode( String p_commProductCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignProductMstRow.TYPE,
            "comm_product_code=?",
            null,
            new Object[] { p_commProductCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCommProductCode(String)}�����{@@link #forRow(CommCampaignProductMstRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCommProductCode( String p_commProductCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCommProductCode( p_commProductCode ) );
    }

}
@
