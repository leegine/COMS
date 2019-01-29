head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TaxRateTableDao.java;


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
 * {@@link TaxRateTableDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TaxRateTableRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TaxRateTablePK 
 * @@see TaxRateTableRow 
 */
public class TaxRateTableDao extends DataAccessObject {


  /** 
   * ����{@@link TaxRateTableDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TaxRateTableRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TaxRateTableRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TaxRateTableDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TaxRateTableDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TaxRateTableRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TaxRateTableRow )
                return new TaxRateTableDao( (TaxRateTableRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TaxRateTableRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TaxRateTableRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TaxRateTableRow}�I�u�W�F�N�g 
    */
    protected TaxRateTableDao( TaxRateTableRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TaxRateTableRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TaxRateTableRow getTaxRateTableRow() {
        return row;
    }


  /** 
   * �w���{@@link TaxRateTableRow}�I�u�W�F�N�g����{@@link TaxRateTableDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TaxRateTableRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TaxRateTableDao}�擾�̂��߂Ɏw���{@@link TaxRateTableRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TaxRateTableDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TaxRateTableDao forRow( TaxRateTableRow row ) throws java.lang.IllegalArgumentException {
        return (TaxRateTableDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TaxRateTableRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TaxRateTableRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TaxRateTablePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TaxRateTableParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TaxRateTableRow.TYPE );
    }


  /** 
   * {@@link TaxRateTableRow}����ӂɓ��肷��{@@link TaxRateTablePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TaxRateTableRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TaxRateTableParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TaxRateTablePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TaxRateTablePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TaxRateTableRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_taxType �����Ώۂł���p_taxType�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TaxRateTableRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TaxRateTableRow findRowByPk( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        TaxRateTablePK pk = new TaxRateTablePK( p_institutionCode, p_taxType, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���TaxRateTablePK�I�u�W�F�N�g����{@@link TaxRateTableRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TaxRateTablePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TaxRateTableRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TaxRateTableRow findRowByPk( TaxRateTablePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TaxRateTableRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,java.sql.Timestamp)}�����{@@link #forRow(TaxRateTableRow)}���g�p���Ă��������B 
   */
    public static TaxRateTableDao findDaoByPk( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        TaxRateTablePK pk = new TaxRateTablePK( p_institutionCode, p_taxType, p_appliStartDate );
        TaxRateTableRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TaxRateTablePK)}�����{@@link #forRow(TaxRateTableRow)}���g�p���Ă��������B 
   */
    public static TaxRateTableDao findDaoByPk( TaxRateTablePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TaxRateTableRow row = findRowByPk( pk );
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
   * p_institutionCode, p_taxType, p_appliStartDate, and �ɂĎw��̒l�����ӂ�{@@link TaxRateTableRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_taxType �����Ώۂł���p_taxType�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_taxType, p_appliStartDate, and �̒l�ƈ�v����{@@link TaxRateTableRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TaxRateTableRow findRowByInstitutionCodeTaxTypeAppliStartDate( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TaxRateTableRow.TYPE,
            "institution_code=? and tax_type=? and appli_start_date=?",
            null,
            new Object[] { p_institutionCode, p_taxType, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TaxRateTableRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TaxRateTableDao.findRowsByInstitutionCodeTaxTypeAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeTaxTypeAppliStartDate(String, String, java.sql.Timestamp)}�����{@@link #forRow(TaxRateTableRow)}���g�p���Ă��������B 
   */
    public static TaxRateTableDao findDaoByInstitutionCodeTaxTypeAppliStartDate( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeTaxTypeAppliStartDate( p_institutionCode, p_taxType, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
