head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvDateDao.java;


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
 * {@@link OtherOrgOutOfSrvDateDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OtherOrgOutOfSrvDateRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OtherOrgOutOfSrvDatePK 
 * @@see OtherOrgOutOfSrvDateRow 
 */
public class OtherOrgOutOfSrvDateDao extends DataAccessObject {


  /** 
   * ����{@@link OtherOrgOutOfSrvDateDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OtherOrgOutOfSrvDateRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OtherOrgOutOfSrvDateRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OtherOrgOutOfSrvDateDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OtherOrgOutOfSrvDateDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OtherOrgOutOfSrvDateRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrgOutOfSrvDateRow )
                return new OtherOrgOutOfSrvDateDao( (OtherOrgOutOfSrvDateRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrgOutOfSrvDateRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrgOutOfSrvDateRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g 
    */
    protected OtherOrgOutOfSrvDateDao( OtherOrgOutOfSrvDateRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OtherOrgOutOfSrvDateRow getOtherOrgOutOfSrvDateRow() {
        return row;
    }


  /** 
   * �w���{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g����{@@link OtherOrgOutOfSrvDateDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OtherOrgOutOfSrvDateRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OtherOrgOutOfSrvDateDao}�擾�̂��߂Ɏw���{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OtherOrgOutOfSrvDateDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OtherOrgOutOfSrvDateDao forRow( OtherOrgOutOfSrvDateRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrgOutOfSrvDateDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrgOutOfSrvDateRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OtherOrgOutOfSrvDateRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OtherOrgOutOfSrvDatePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OtherOrgOutOfSrvDateParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrgOutOfSrvDateRow.TYPE );
    }


  /** 
   * {@@link OtherOrgOutOfSrvDateRow}����ӂɓ��肷��{@@link OtherOrgOutOfSrvDatePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OtherOrgOutOfSrvDateRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OtherOrgOutOfSrvDateParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OtherOrgOutOfSrvDatePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OtherOrgOutOfSrvDatePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_otherOrgCode �����Ώۂł���p_otherOrgCode�t�B�[���h�̒l
   * @@param p_suspensionDate �����Ώۂł���p_suspensionDate�t�B�[���h�̒l
   * @@param p_suspensionStartTime �����Ώۂł���p_suspensionStartTime�t�B�[���h�̒l
   * @@param p_suspensionEndTime �����Ώۂł���p_suspensionEndTime�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OtherOrgOutOfSrvDateRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OtherOrgOutOfSrvDateRow findRowByPk( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvDatePK pk = new OtherOrgOutOfSrvDatePK( p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime );
        return findRowByPk( pk );
    }


  /** 
   * �w���OtherOrgOutOfSrvDatePK�I�u�W�F�N�g����{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OtherOrgOutOfSrvDatePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OtherOrgOutOfSrvDateRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OtherOrgOutOfSrvDateRow findRowByPk( OtherOrgOutOfSrvDatePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrgOutOfSrvDateRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(OtherOrgOutOfSrvDateRow)}���g�p���Ă��������B 
   */
    public static OtherOrgOutOfSrvDateDao findDaoByPk( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvDatePK pk = new OtherOrgOutOfSrvDatePK( p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime );
        OtherOrgOutOfSrvDateRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OtherOrgOutOfSrvDatePK)}�����{@@link #forRow(OtherOrgOutOfSrvDateRow)}���g�p���Ă��������B 
   */
    public static OtherOrgOutOfSrvDateDao findDaoByPk( OtherOrgOutOfSrvDatePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgOutOfSrvDateRow row = findRowByPk( pk );
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
   * p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime, and �ɂĎw��̒l�����ӂ�{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_otherOrgCode �����Ώۂł���p_otherOrgCode�t�B�[���h�̒l
   * @@param p_suspensionDate �����Ώۂł���p_suspensionDate�t�B�[���h�̒l
   * @@param p_suspensionStartTime �����Ώۂł���p_suspensionStartTime�t�B�[���h�̒l
   * @@param p_suspensionEndTime �����Ώۂł���p_suspensionEndTime�t�B�[���h�̒l
   * 
   * @@return �����w���p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime, and �̒l�ƈ�v����{@@link OtherOrgOutOfSrvDateRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OtherOrgOutOfSrvDateRow findRowByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrgOutOfSrvDateRow.TYPE,
            "other_org_code=? and suspension_date=? and suspension_start_time=? and suspension_end_time=?",
            null,
            new Object[] { p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrgOutOfSrvDateRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrgOutOfSrvDateDao.findRowsByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime(String, String, String, String)}�����{@@link #forRow(OtherOrgOutOfSrvDateRow)}���g�p���Ă��������B 
   */
    public static OtherOrgOutOfSrvDateDao findDaoByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime( String p_otherOrgCode, String p_suspensionDate, String p_suspensionStartTime, String p_suspensionEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOtherOrgCodeSuspensionDateSuspensionStartTimeSuspensionEndTime( p_otherOrgCode, p_suspensionDate, p_suspensionStartTime, p_suspensionEndTime ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
