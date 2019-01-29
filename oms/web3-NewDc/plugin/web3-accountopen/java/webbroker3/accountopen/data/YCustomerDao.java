head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.20.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	YCustomerDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link YCustomerDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link YCustomerRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see YCustomerPK 
 * @@see YCustomerRow 
 */
public class YCustomerDao extends DataAccessObject {


  /** 
   * ����{@@link YCustomerDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private YCustomerRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link YCustomerRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link YCustomerDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link YCustomerDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link YCustomerRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof YCustomerRow )
                return new YCustomerDao( (YCustomerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a YCustomerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link YCustomerRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link YCustomerRow}�I�u�W�F�N�g 
    */
    protected YCustomerDao( YCustomerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link YCustomerRow}�I�u�W�F�N�g���擾���܂��B
   */
    public YCustomerRow getYCustomerRow() {
        return row;
    }


  /** 
   * �w���{@@link YCustomerRow}�I�u�W�F�N�g����{@@link YCustomerDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link YCustomerRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link YCustomerDao}�擾�̂��߂Ɏw���{@@link YCustomerRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link YCustomerDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static YCustomerDao forRow( YCustomerRow row ) throws java.lang.IllegalArgumentException {
        return (YCustomerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link YCustomerRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link YCustomerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link YCustomerPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link YCustomerParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( YCustomerRow.TYPE );
    }


  /** 
   * {@@link YCustomerRow}����ӂɓ��肷��{@@link YCustomerPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link YCustomerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link YCustomerParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link YCustomerPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static YCustomerPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new YCustomerPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link YCustomerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_yCustomerId �����Ώۂł���p_yCustomerId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link YCustomerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static YCustomerRow findRowByPk( long p_yCustomerId ) throws DataFindException, DataQueryException, DataNetworkException {
        YCustomerPK pk = new YCustomerPK( p_yCustomerId );
        return findRowByPk( pk );
    }


  /** 
   * �w���YCustomerPK�I�u�W�F�N�g����{@@link YCustomerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����YCustomerPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link YCustomerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static YCustomerRow findRowByPk( YCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (YCustomerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(YCustomerRow)}���g�p���Ă��������B 
   */
    public static YCustomerDao findDaoByPk( long p_yCustomerId ) throws DataFindException, DataQueryException, DataNetworkException {
        YCustomerPK pk = new YCustomerPK( p_yCustomerId );
        YCustomerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(YCustomerPK)}�����{@@link #forRow(YCustomerRow)}���g�p���Ă��������B 
   */
    public static YCustomerDao findDaoByPk( YCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        YCustomerRow row = findRowByPk( pk );
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
   * p_yCustomerId, and �ɂĎw��̒l�����ӂ�{@@link YCustomerRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_yCustomerId �����Ώۂł���p_yCustomerId�t�B�[���h�̒l
   * 
   * @@return �����w���p_yCustomerId, and �̒l�ƈ�v����{@@link YCustomerRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static YCustomerRow findRowByYCustomerId( long p_yCustomerId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            YCustomerRow.TYPE,
            "y_customer_id=?",
            null,
            new Object[] { new Long(p_yCustomerId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (YCustomerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query YCustomerDao.findRowsByYCustomerId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByYCustomerId(long)}�����{@@link #forRow(YCustomerRow)}���g�p���Ă��������B 
   */
    public static YCustomerDao findDaoByYCustomerId( long p_yCustomerId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByYCustomerId( p_yCustomerId ) );
    }


  /** 
   * p_controlBranchCode, p_operationDiv, p_controlNumber, p_institutionCode, and �ɂĎw��̒l�����ӂ�{@@link YCustomerRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_controlBranchCode �����Ώۂł���p_controlBranchCode�t�B�[���h�̒l
   * @@param p_operationDiv �����Ώۂł���p_operationDiv�t�B�[���h�̒l
   * @@param p_controlNumber �����Ώۂł���p_controlNumber�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_controlBranchCode, p_operationDiv, p_controlNumber, p_institutionCode, and �̒l�ƈ�v����{@@link YCustomerRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static YCustomerRow findRowByControlBranchCodeOperationDivControlNumberInstitutionCode( String p_controlBranchCode, String p_operationDiv, int p_controlNumber, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            YCustomerRow.TYPE,
            "control_branch_code=? and operation_div=? and control_number=? and institution_code=?",
            null,
            new Object[] { p_controlBranchCode, p_operationDiv, new Integer(p_controlNumber), p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (YCustomerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query YCustomerDao.findRowsByControlBranchCodeOperationDivControlNumberInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByControlBranchCodeOperationDivControlNumberInstitutionCode(String, String, int, String)}�����{@@link #forRow(YCustomerRow)}���g�p���Ă��������B 
   */
    public static YCustomerDao findDaoByControlBranchCodeOperationDivControlNumberInstitutionCode( String p_controlBranchCode, String p_operationDiv, int p_controlNumber, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByControlBranchCodeOperationDivControlNumberInstitutionCode( p_controlBranchCode, p_operationDiv, p_controlNumber, p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and �ɂĎw��̒l�Ɉ�v����{@@link YCustomerRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_eraBorn �����Ώۂł���p_eraBorn�t�B�[���h�̒l
   * @@param p_bornDate �����Ώۂł���p_bornDate�t�B�[���h�̒l
   * @@param p_nameKana �����Ώۂł���p_nameKana�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and �̒l�ƈ�v����{@@link YCustomerRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            YCustomerRow.TYPE,
            "institution_code=? and era_born=? and born_date=? and name_kana=? and name=?",
            null,
            new Object[] { p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeEraBornBornDateNameKanaName(String, String, String, String, String)}�����{@@link #forRow(YCustomerRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeEraBornBornDateNameKanaName( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name ) );
    }

}
@
