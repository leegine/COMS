head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgSrvTimeDao.java;


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
 * {@@link OtherOrgSrvTimeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OtherOrgSrvTimeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OtherOrgSrvTimePK 
 * @@see OtherOrgSrvTimeRow 
 */
public class OtherOrgSrvTimeDao extends DataAccessObject {


  /** 
   * ����{@@link OtherOrgSrvTimeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OtherOrgSrvTimeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OtherOrgSrvTimeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OtherOrgSrvTimeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OtherOrgSrvTimeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OtherOrgSrvTimeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OtherOrgSrvTimeRow )
                return new OtherOrgSrvTimeDao( (OtherOrgSrvTimeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OtherOrgSrvTimeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OtherOrgSrvTimeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g 
    */
    protected OtherOrgSrvTimeDao( OtherOrgSrvTimeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OtherOrgSrvTimeRow getOtherOrgSrvTimeRow() {
        return row;
    }


  /** 
   * �w���{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g����{@@link OtherOrgSrvTimeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OtherOrgSrvTimeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OtherOrgSrvTimeDao}�擾�̂��߂Ɏw���{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OtherOrgSrvTimeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OtherOrgSrvTimeDao forRow( OtherOrgSrvTimeRow row ) throws java.lang.IllegalArgumentException {
        return (OtherOrgSrvTimeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OtherOrgSrvTimeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OtherOrgSrvTimeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OtherOrgSrvTimePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OtherOrgSrvTimeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OtherOrgSrvTimeRow.TYPE );
    }


  /** 
   * {@@link OtherOrgSrvTimeRow}����ӂɓ��肷��{@@link OtherOrgSrvTimePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OtherOrgSrvTimeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OtherOrgSrvTimeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OtherOrgSrvTimePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OtherOrgSrvTimePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_otherOrgCode �����Ώۂł���p_otherOrgCode�t�B�[���h�̒l
   * @@param p_weekDiv �����Ώۂł���p_weekDiv�t�B�[���h�̒l
   * @@param p_serviceStartTime �����Ώۂł���p_serviceStartTime�t�B�[���h�̒l
   * @@param p_serviceEndTime �����Ώۂł���p_serviceEndTime�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OtherOrgSrvTimeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OtherOrgSrvTimeRow findRowByPk( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgSrvTimePK pk = new OtherOrgSrvTimePK( p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime );
        return findRowByPk( pk );
    }


  /** 
   * �w���OtherOrgSrvTimePK�I�u�W�F�N�g����{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OtherOrgSrvTimePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OtherOrgSrvTimeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OtherOrgSrvTimeRow findRowByPk( OtherOrgSrvTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OtherOrgSrvTimeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(OtherOrgSrvTimeRow)}���g�p���Ă��������B 
   */
    public static OtherOrgSrvTimeDao findDaoByPk( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgSrvTimePK pk = new OtherOrgSrvTimePK( p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime );
        OtherOrgSrvTimeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OtherOrgSrvTimePK)}�����{@@link #forRow(OtherOrgSrvTimeRow)}���g�p���Ă��������B 
   */
    public static OtherOrgSrvTimeDao findDaoByPk( OtherOrgSrvTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OtherOrgSrvTimeRow row = findRowByPk( pk );
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
   * p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime, and �ɂĎw��̒l�����ӂ�{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_otherOrgCode �����Ώۂł���p_otherOrgCode�t�B�[���h�̒l
   * @@param p_weekDiv �����Ώۂł���p_weekDiv�t�B�[���h�̒l
   * @@param p_serviceStartTime �����Ώۂł���p_serviceStartTime�t�B�[���h�̒l
   * @@param p_serviceEndTime �����Ώۂł���p_serviceEndTime�t�B�[���h�̒l
   * 
   * @@return �����w���p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime, and �̒l�ƈ�v����{@@link OtherOrgSrvTimeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OtherOrgSrvTimeRow findRowByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OtherOrgSrvTimeRow.TYPE,
            "other_org_code=? and week_div=? and service_start_time=? and service_end_time=?",
            null,
            new Object[] { p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OtherOrgSrvTimeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OtherOrgSrvTimeDao.findRowsByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime(String, String, String, String)}�����{@@link #forRow(OtherOrgSrvTimeRow)}���g�p���Ă��������B 
   */
    public static OtherOrgSrvTimeDao findDaoByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOtherOrgCodeWeekDivServiceStartTimeServiceEndTime( p_otherOrgCode, p_weekDiv, p_serviceStartTime, p_serviceEndTime ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
