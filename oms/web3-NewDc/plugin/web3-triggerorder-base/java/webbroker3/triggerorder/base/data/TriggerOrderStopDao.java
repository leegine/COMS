head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	TriggerOrderStopDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.triggerorder.base.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link TriggerOrderStopDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TriggerOrderStopRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TriggerOrderStopPK 
 * @@see TriggerOrderStopRow 
 */
public class TriggerOrderStopDao extends DataAccessObject {


  /** 
   * ����{@@link TriggerOrderStopDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TriggerOrderStopRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TriggerOrderStopRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TriggerOrderStopDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TriggerOrderStopDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TriggerOrderStopRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TriggerOrderStopRow )
                return new TriggerOrderStopDao( (TriggerOrderStopRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TriggerOrderStopRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TriggerOrderStopRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TriggerOrderStopRow}�I�u�W�F�N�g 
    */
    protected TriggerOrderStopDao( TriggerOrderStopRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TriggerOrderStopRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TriggerOrderStopRow getTriggerOrderStopRow() {
        return row;
    }


  /** 
   * �w���{@@link TriggerOrderStopRow}�I�u�W�F�N�g����{@@link TriggerOrderStopDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TriggerOrderStopRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TriggerOrderStopDao}�擾�̂��߂Ɏw���{@@link TriggerOrderStopRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TriggerOrderStopDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TriggerOrderStopDao forRow( TriggerOrderStopRow row ) throws java.lang.IllegalArgumentException {
        return (TriggerOrderStopDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TriggerOrderStopRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TriggerOrderStopRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TriggerOrderStopPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TriggerOrderStopParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TriggerOrderStopRow.TYPE );
    }


  /** 
   * {@@link TriggerOrderStopRow}����ӂɓ��肷��{@@link TriggerOrderStopPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TriggerOrderStopRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TriggerOrderStopParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TriggerOrderStopPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TriggerOrderStopPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TriggerOrderStopPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TriggerOrderStopRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_triggerOrderStopId �����Ώۂł���p_triggerOrderStopId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TriggerOrderStopRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TriggerOrderStopRow findRowByPk( long p_triggerOrderStopId ) throws DataFindException, DataQueryException, DataNetworkException {
        TriggerOrderStopPK pk = new TriggerOrderStopPK( p_triggerOrderStopId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TriggerOrderStopPK�I�u�W�F�N�g����{@@link TriggerOrderStopRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TriggerOrderStopPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TriggerOrderStopRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TriggerOrderStopRow findRowByPk( TriggerOrderStopPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TriggerOrderStopRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TriggerOrderStopRow)}���g�p���Ă��������B 
   */
    public static TriggerOrderStopDao findDaoByPk( long p_triggerOrderStopId ) throws DataFindException, DataQueryException, DataNetworkException {
        TriggerOrderStopPK pk = new TriggerOrderStopPK( p_triggerOrderStopId );
        TriggerOrderStopRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TriggerOrderStopPK)}�����{@@link #forRow(TriggerOrderStopRow)}���g�p���Ă��������B 
   */
    public static TriggerOrderStopDao findDaoByPk( TriggerOrderStopPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TriggerOrderStopRow row = findRowByPk( pk );
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
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_triggerOrderStopId, and �ɂĎw��̒l�����ӂ�{@@link TriggerOrderStopRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_triggerOrderStopId �����Ώۂł���p_triggerOrderStopId�t�B�[���h�̒l
   * 
   * @@return �����w���p_triggerOrderStopId, and �̒l�ƈ�v����{@@link TriggerOrderStopRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TriggerOrderStopRow findRowByTriggerOrderStopId( long p_triggerOrderStopId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TriggerOrderStopRow.TYPE,
            "trigger_order_stop_id=?",
            null,
            new Object[] { new Long(p_triggerOrderStopId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TriggerOrderStopRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TriggerOrderStopDao.findRowsByTriggerOrderStopId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTriggerOrderStopId(long)}�����{@@link #forRow(TriggerOrderStopRow)}���g�p���Ă��������B 
   */
    public static TriggerOrderStopDao findDaoByTriggerOrderStopId( long p_triggerOrderStopId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTriggerOrderStopId( p_triggerOrderStopId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_targetType, p_key, and �ɂĎw��̒l�Ɉ�v����{@@link TriggerOrderStopRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_targetType �����Ώۂł���p_targetType�t�B�[���h�̒l
   * @@param p_key �����Ώۂł���p_key�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_targetType, p_key, and �̒l�ƈ�v����{@@link TriggerOrderStopRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeTargetTypeKey( String p_institutionCode, String p_branchCode, String p_targetType, String p_key ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TriggerOrderStopRow.TYPE,
            "institution_code=? and branch_code=? and target_type=? and key=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_targetType, p_key } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeTargetTypeKey(String, String, String, String)}�����{@@link #forRow(TriggerOrderStopRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeTargetTypeKey( String p_institutionCode, String p_branchCode, String p_targetType, String p_key ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeTargetTypeKey( p_institutionCode, p_branchCode, p_targetType, p_key ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_targetType, p_deleteFlag, and �ɂĎw��̒l�Ɉ�v����{@@link TriggerOrderStopRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_targetType �����Ώۂł���p_targetType�t�B�[���h�̒l
   * @@param p_deleteFlag �����Ώۂł���p_deleteFlag�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_targetType, p_deleteFlag, and �̒l�ƈ�v����{@@link TriggerOrderStopRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeTargetTypeDeleteFlag( String p_institutionCode, String p_branchCode, String p_targetType, int p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TriggerOrderStopRow.TYPE,
            "institution_code=? and branch_code=? and target_type=? and delete_flag=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_targetType, new Integer(p_deleteFlag) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeTargetTypeDeleteFlag(String, String, String, int)}�����{@@link #forRow(TriggerOrderStopRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeTargetTypeDeleteFlag( String p_institutionCode, String p_branchCode, String p_targetType, int p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeTargetTypeDeleteFlag( p_institutionCode, p_branchCode, p_targetType, p_deleteFlag ) );
    }

}
@
