head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	AmountRangeDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link AmountRangeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AmountRangeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AmountRangePK 
 * @@see AmountRangeRow 
 */
public class AmountRangeDao extends DataAccessObject {


  /** 
   * ����{@@link AmountRangeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AmountRangeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AmountRangeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AmountRangeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AmountRangeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AmountRangeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AmountRangeRow )
                return new AmountRangeDao( (AmountRangeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AmountRangeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AmountRangeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AmountRangeRow}�I�u�W�F�N�g 
    */
    protected AmountRangeDao( AmountRangeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AmountRangeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AmountRangeRow getAmountRangeRow() {
        return row;
    }


  /** 
   * �w���{@@link AmountRangeRow}�I�u�W�F�N�g����{@@link AmountRangeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AmountRangeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AmountRangeDao}�擾�̂��߂Ɏw���{@@link AmountRangeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AmountRangeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AmountRangeDao forRow( AmountRangeRow row ) throws java.lang.IllegalArgumentException {
        return (AmountRangeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AmountRangeRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AmountRangeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AmountRangePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AmountRangeParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AmountRangeRow.TYPE );
    }


  /** 
   * {@@link AmountRangeRow}����ӂɓ��肷��{@@link AmountRangePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AmountRangeRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AmountRangeParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AmountRangePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AmountRangePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AmountRangeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_fundType �����Ώۂł���p_fundType�t�B�[���h�̒l
   * @@param p_transactionType �����Ώۂł���p_transactionType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AmountRangeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AmountRangeRow findRowByPk( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataFindException, DataQueryException, DataNetworkException {
        AmountRangePK pk = new AmountRangePK( p_institutionCode, p_fundType, p_transactionType );
        return findRowByPk( pk );
    }


  /** 
   * �w���AmountRangePK�I�u�W�F�N�g����{@@link AmountRangeRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AmountRangePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AmountRangeRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AmountRangeRow findRowByPk( AmountRangePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AmountRangeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(AmountRangeRow)}���g�p���Ă��������B 
   */
    public static AmountRangeDao findDaoByPk( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataFindException, DataQueryException, DataNetworkException {
        AmountRangePK pk = new AmountRangePK( p_institutionCode, p_fundType, p_transactionType );
        AmountRangeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AmountRangePK)}�����{@@link #forRow(AmountRangeRow)}���g�p���Ă��������B 
   */
    public static AmountRangeDao findDaoByPk( AmountRangePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AmountRangeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_fundType, p_transactionType, and �ɂĎw��̒l�����ӂ�{@@link AmountRangeRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_fundType �����Ώۂł���p_fundType�t�B�[���h�̒l
   * @@param p_transactionType �����Ώۂł���p_transactionType�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_fundType, p_transactionType, and �̒l�ƈ�v����{@@link AmountRangeRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AmountRangeRow findRowByInstitutionCodeFundTypeTransactionType( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AmountRangeRow.TYPE,
            "institution_code=? and fund_type=? and transaction_type=?",
            null,
            new Object[] { p_institutionCode, p_fundType, p_transactionType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AmountRangeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AmountRangeDao.findRowsByInstitutionCodeFundTypeTransactionType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeFundTypeTransactionType(String, String, String)}�����{@@link #forRow(AmountRangeRow)}���g�p���Ă��������B 
   */
    public static AmountRangeDao findDaoByInstitutionCodeFundTypeTransactionType( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeFundTypeTransactionType( p_institutionCode, p_fundType, p_transactionType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
