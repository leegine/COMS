head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExchangeRateDao.java;


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
 * {@@link ExchangeRateDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link ExchangeRateRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see ExchangeRatePK 
 * @@see ExchangeRateRow 
 */
public class ExchangeRateDao extends DataAccessObject {


  /** 
   * ����{@@link ExchangeRateDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private ExchangeRateRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link ExchangeRateRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link ExchangeRateDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link ExchangeRateDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link ExchangeRateRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExchangeRateRow )
                return new ExchangeRateDao( (ExchangeRateRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExchangeRateRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExchangeRateRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link ExchangeRateRow}�I�u�W�F�N�g 
    */
    protected ExchangeRateDao( ExchangeRateRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link ExchangeRateRow}�I�u�W�F�N�g���擾���܂��B
   */
    public ExchangeRateRow getExchangeRateRow() {
        return row;
    }


  /** 
   * �w���{@@link ExchangeRateRow}�I�u�W�F�N�g����{@@link ExchangeRateDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link ExchangeRateRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link ExchangeRateDao}�擾�̂��߂Ɏw���{@@link ExchangeRateRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link ExchangeRateDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static ExchangeRateDao forRow( ExchangeRateRow row ) throws java.lang.IllegalArgumentException {
        return (ExchangeRateDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExchangeRateRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link ExchangeRateRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link ExchangeRatePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link ExchangeRateParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExchangeRateRow.TYPE );
    }


  /** 
   * {@@link ExchangeRateRow}����ӂɓ��肷��{@@link ExchangeRatePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link ExchangeRateRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link ExchangeRateParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link ExchangeRatePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static ExchangeRatePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link ExchangeRateRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_currencyCode �����Ώۂł���p_currencyCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExchangeRateRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExchangeRateRow findRowByPk( String p_institutionCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ExchangeRatePK pk = new ExchangeRatePK( p_institutionCode, p_currencyCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���ExchangeRatePK�I�u�W�F�N�g����{@@link ExchangeRateRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����ExchangeRatePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExchangeRateRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExchangeRateRow findRowByPk( ExchangeRatePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExchangeRateRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(ExchangeRateRow)}���g�p���Ă��������B 
   */
    public static ExchangeRateDao findDaoByPk( String p_institutionCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ExchangeRatePK pk = new ExchangeRatePK( p_institutionCode, p_currencyCode );
        ExchangeRateRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(ExchangeRatePK)}�����{@@link #forRow(ExchangeRateRow)}���g�p���Ă��������B 
   */
    public static ExchangeRateDao findDaoByPk( ExchangeRatePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExchangeRateRow row = findRowByPk( pk );
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
   * p_institutionCode, p_currencyCode, and �ɂĎw��̒l�����ӂ�{@@link ExchangeRateRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_currencyCode �����Ώۂł���p_currencyCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_currencyCode, and �̒l�ƈ�v����{@@link ExchangeRateRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static ExchangeRateRow findRowByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExchangeRateRow.TYPE,
            "institution_code=? and currency_code=?",
            null,
            new Object[] { p_institutionCode, p_currencyCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExchangeRateRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExchangeRateDao.findRowsByInstitutionCodeCurrencyCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeCurrencyCode(String, String)}�����{@@link #forRow(ExchangeRateRow)}���g�p���Ă��������B 
   */
    public static ExchangeRateDao findDaoByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCurrencyCode( p_institutionCode, p_currencyCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
