head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.17.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenDlFormMasterDao.java;


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
 * {@@link AccOpenDlFormMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenDlFormMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenDlFormMasterPK 
 * @@see AccOpenDlFormMasterRow 
 */
public class AccOpenDlFormMasterDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenDlFormMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenDlFormMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenDlFormMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenDlFormMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenDlFormMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenDlFormMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenDlFormMasterRow )
                return new AccOpenDlFormMasterDao( (AccOpenDlFormMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenDlFormMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenDlFormMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g 
    */
    protected AccOpenDlFormMasterDao( AccOpenDlFormMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenDlFormMasterRow getAccOpenDlFormMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g����{@@link AccOpenDlFormMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenDlFormMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenDlFormMasterDao}�擾�̂��߂Ɏw���{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenDlFormMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenDlFormMasterDao forRow( AccOpenDlFormMasterRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenDlFormMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenDlFormMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenDlFormMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenDlFormMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenDlFormMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenDlFormMasterRow.TYPE );
    }


  /** 
   * {@@link AccOpenDlFormMasterRow}����ӂɓ��肷��{@@link AccOpenDlFormMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenDlFormMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenDlFormMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenDlFormMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenDlFormMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_columnNumber �����Ώۂł���p_columnNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenDlFormMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenDlFormMasterRow findRowByPk( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenDlFormMasterPK pk = new AccOpenDlFormMasterPK( p_institutionCode, p_accountDiv, p_columnNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenDlFormMasterPK�I�u�W�F�N�g����{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenDlFormMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenDlFormMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenDlFormMasterRow findRowByPk( AccOpenDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenDlFormMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,int)}�����{@@link #forRow(AccOpenDlFormMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenDlFormMasterDao findDaoByPk( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenDlFormMasterPK pk = new AccOpenDlFormMasterPK( p_institutionCode, p_accountDiv, p_columnNumber );
        AccOpenDlFormMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenDlFormMasterPK)}�����{@@link #forRow(AccOpenDlFormMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenDlFormMasterDao findDaoByPk( AccOpenDlFormMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenDlFormMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accountDiv, p_columnNumber, and �ɂĎw��̒l�����ӂ�{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accountDiv �����Ώۂł���p_accountDiv�t�B�[���h�̒l
   * @@param p_columnNumber �����Ώۂł���p_columnNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_accountDiv, p_columnNumber, and �̒l�ƈ�v����{@@link AccOpenDlFormMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenDlFormMasterRow findRowByInstitutionCodeAccountDivColumnNumber( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenDlFormMasterRow.TYPE,
            "institution_code=? and account_div=? and column_number=?",
            null,
            new Object[] { p_institutionCode, p_accountDiv, new Integer(p_columnNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenDlFormMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenDlFormMasterDao.findRowsByInstitutionCodeAccountDivColumnNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeAccountDivColumnNumber(String, String, int)}�����{@@link #forRow(AccOpenDlFormMasterRow)}���g�p���Ă��������B 
   */
    public static AccOpenDlFormMasterDao findDaoByInstitutionCodeAccountDivColumnNumber( String p_institutionCode, String p_accountDiv, int p_columnNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccountDivColumnNumber( p_institutionCode, p_accountDiv, p_columnNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
