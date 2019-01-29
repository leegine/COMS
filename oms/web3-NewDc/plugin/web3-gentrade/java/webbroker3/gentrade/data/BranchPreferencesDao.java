head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchPreferencesDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BranchPreferencesDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BranchPreferencesRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BranchPreferencesPK 
 * @@see BranchPreferencesRow 
 */
public class BranchPreferencesDao extends DataAccessObject {


  /** 
   * ����{@@link BranchPreferencesDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BranchPreferencesRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BranchPreferencesRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BranchPreferencesDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BranchPreferencesDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BranchPreferencesRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BranchPreferencesRow )
                return new BranchPreferencesDao( (BranchPreferencesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BranchPreferencesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BranchPreferencesRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BranchPreferencesRow}�I�u�W�F�N�g 
    */
    protected BranchPreferencesDao( BranchPreferencesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BranchPreferencesRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BranchPreferencesRow getBranchPreferencesRow() {
        return row;
    }


  /** 
   * �w���{@@link BranchPreferencesRow}�I�u�W�F�N�g����{@@link BranchPreferencesDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BranchPreferencesRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BranchPreferencesDao}�擾�̂��߂Ɏw���{@@link BranchPreferencesRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BranchPreferencesDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BranchPreferencesDao forRow( BranchPreferencesRow row ) throws java.lang.IllegalArgumentException {
        return (BranchPreferencesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BranchPreferencesRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BranchPreferencesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BranchPreferencesPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BranchPreferencesParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BranchPreferencesRow.TYPE );
    }


  /** 
   * {@@link BranchPreferencesRow}����ӂɓ��肷��{@@link BranchPreferencesPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BranchPreferencesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BranchPreferencesParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BranchPreferencesPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BranchPreferencesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BranchPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * @@param p_nameSerialNo �����Ώۂł���p_nameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BranchPreferencesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BranchPreferencesRow findRowByPk( long p_branchId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchPreferencesPK pk = new BranchPreferencesPK( p_branchId, p_name, p_nameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���BranchPreferencesPK�I�u�W�F�N�g����{@@link BranchPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BranchPreferencesPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BranchPreferencesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BranchPreferencesRow findRowByPk( BranchPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BranchPreferencesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,int)}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public static BranchPreferencesDao findDaoByPk( long p_branchId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchPreferencesPK pk = new BranchPreferencesPK( p_branchId, p_name, p_nameSerialNo );
        BranchPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BranchPreferencesPK)}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public static BranchPreferencesDao findDaoByPk( BranchPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link BranchPreferencesDao}�ɕR�t��{@@link BranchPreferencesRow}���ŊO���L�[�̊֌W������{@@link BranchRow}���������܂��B 
   * 
   * @@return {@@link BranchPreferencesDao}�ƊO���L�[�̊֌W�ɂ���{@@link BranchRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public BranchRow fetchBranchRowViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        Row row = BranchDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BranchRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BranchRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBranchRowViaBranchId()}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByBranchId(BranchRow)}���g�p���Ă��������B 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}�ƊO���L�[�̊֌W�ɂ���{@@link BranchPreferencesRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BranchRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BranchRow}�ɊO���L�[������{@@link BranchPreferencesRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}�ƊO���L�[�̊֌W�ɂ���{@@link BranchPreferencesRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BranchPK}�I�u�W�F�N�g 
   * @@return {@@link BranchPK}�ƊO���L�[����v����l������{@@link BranchPreferencesRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link BranchPreferencesRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link BranchPreferencesRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BranchPreferencesRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchPK)}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(long)}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
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
   * p_branchId, p_name, p_nameSerialNo, and �ɂĎw��̒l�����ӂ�{@@link BranchPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * @@param p_nameSerialNo �����Ώۂł���p_nameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_branchId, p_name, p_nameSerialNo, and �̒l�ƈ�v����{@@link BranchPreferencesRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BranchPreferencesRow findRowByBranchIdNameNameSerialNo( long p_branchId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BranchPreferencesRow.TYPE,
            "branch_id=? and name=? and name_serial_no=?",
            null,
            new Object[] { new Long(p_branchId), p_name, new Integer(p_nameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BranchPreferencesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BranchPreferencesDao.findRowsByBranchIdNameNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBranchIdNameNameSerialNo(long, String, int)}�����{@@link #forRow(BranchPreferencesRow)}���g�p���Ă��������B 
   */
    public static BranchPreferencesDao findDaoByBranchIdNameNameSerialNo( long p_branchId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchIdNameNameSerialNo( p_branchId, p_name, p_nameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
