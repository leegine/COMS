head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExtMailProcTempDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ExtMailProcTempDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link ExtMailProcTempRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see ExtMailProcTempPK 
 * @@see ExtMailProcTempRow 
 */
public class ExtMailProcTempDao extends DataAccessObject {


  /** 
   * ����{@@link ExtMailProcTempDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private ExtMailProcTempRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link ExtMailProcTempRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link ExtMailProcTempDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link ExtMailProcTempDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link ExtMailProcTempRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExtMailProcTempRow )
                return new ExtMailProcTempDao( (ExtMailProcTempRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExtMailProcTempRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExtMailProcTempRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link ExtMailProcTempRow}�I�u�W�F�N�g 
    */
    protected ExtMailProcTempDao( ExtMailProcTempRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link ExtMailProcTempRow}�I�u�W�F�N�g���擾���܂��B
   */
    public ExtMailProcTempRow getExtMailProcTempRow() {
        return row;
    }


  /** 
   * �w���{@@link ExtMailProcTempRow}�I�u�W�F�N�g����{@@link ExtMailProcTempDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link ExtMailProcTempRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link ExtMailProcTempDao}�擾�̂��߂Ɏw���{@@link ExtMailProcTempRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link ExtMailProcTempDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static ExtMailProcTempDao forRow( ExtMailProcTempRow row ) throws java.lang.IllegalArgumentException {
        return (ExtMailProcTempDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExtMailProcTempRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link ExtMailProcTempRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link ExtMailProcTempPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link ExtMailProcTempParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExtMailProcTempRow.TYPE );
    }


  /** 
   * {@@link ExtMailProcTempRow}����ӂɓ��肷��{@@link ExtMailProcTempPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link ExtMailProcTempRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link ExtMailProcTempParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link ExtMailProcTempPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static ExtMailProcTempPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link ExtMailProcTempRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_sendmailDiv �����Ώۂł���p_sendmailDiv�t�B�[���h�̒l
   * @@param p_discernmentId �����Ώۂł���p_discernmentId�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_mailId �����Ώۂł���p_mailId�t�B�[���h�̒l
   * @@param p_itemName �����Ώۂł���p_itemName�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExtMailProcTempRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExtMailProcTempRow findRowByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataFindException, DataQueryException, DataNetworkException {
        ExtMailProcTempPK pk = new ExtMailProcTempPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName );
        return findRowByPk( pk );
    }


  /** 
   * �w���ExtMailProcTempPK�I�u�W�F�N�g����{@@link ExtMailProcTempRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����ExtMailProcTempPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExtMailProcTempRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExtMailProcTempRow findRowByPk( ExtMailProcTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExtMailProcTempRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String,long,String)}�����{@@link #forRow(ExtMailProcTempRow)}���g�p���Ă��������B 
   */
    public static ExtMailProcTempDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataFindException, DataQueryException, DataNetworkException {
        ExtMailProcTempPK pk = new ExtMailProcTempPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName );
        ExtMailProcTempRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(ExtMailProcTempPK)}�����{@@link #forRow(ExtMailProcTempRow)}���g�p���Ă��������B 
   */
    public static ExtMailProcTempDao findDaoByPk( ExtMailProcTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExtMailProcTempRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName, and �ɂĎw��̒l�����ӂ�{@@link ExtMailProcTempRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_sendmailDiv �����Ώۂł���p_sendmailDiv�t�B�[���h�̒l
   * @@param p_discernmentId �����Ώۂł���p_discernmentId�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_mailId �����Ώۂł���p_mailId�t�B�[���h�̒l
   * @@param p_itemName �����Ώۂł���p_itemName�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName, and �̒l�ƈ�v����{@@link ExtMailProcTempRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static ExtMailProcTempRow findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExtMailProcTempRow.TYPE,
            "institution_code=? and branch_code=? and sendmail_div=? and discernment_id=? and account_code=? and mail_id=? and item_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, new Long(p_mailId), p_itemName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExtMailProcTempRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExtMailProcTempDao.findRowsByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName(String, String, String, String, String, long, String)}�����{@@link #forRow(ExtMailProcTempRow)}���g�p���Ă��������B 
   */
    public static ExtMailProcTempDao findDaoByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_sendmailDiv, p_mailId, and �ɂĎw��̒l�Ɉ�v����{@@link ExtMailProcTempRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_sendmailDiv �����Ώۂł���p_sendmailDiv�t�B�[���h�̒l
   * @@param p_mailId �����Ώۂł���p_mailId�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_sendmailDiv, p_mailId, and �̒l�ƈ�v����{@@link ExtMailProcTempRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeSendmailDivMailId( String p_institutionCode, String p_sendmailDiv, long p_mailId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExtMailProcTempRow.TYPE,
            "institution_code=? and sendmail_div=? and mail_id=?",
            null,
            new Object[] { p_institutionCode, p_sendmailDiv, new Long(p_mailId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeSendmailDivMailId(String, String, long)}�����{@@link #forRow(ExtMailProcTempRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeSendmailDivMailId( String p_institutionCode, String p_sendmailDiv, long p_mailId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeSendmailDivMailId( p_institutionCode, p_sendmailDiv, p_mailId ) );
    }

}
@
