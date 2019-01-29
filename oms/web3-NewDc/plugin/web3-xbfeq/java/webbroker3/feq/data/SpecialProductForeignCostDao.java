head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	SpecialProductForeignCostDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SpecialProductForeignCostDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SpecialProductForeignCostRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SpecialProductForeignCostPK 
 * @@see SpecialProductForeignCostRow 
 */
public class SpecialProductForeignCostDao extends DataAccessObject {


  /** 
   * ����{@@link SpecialProductForeignCostDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SpecialProductForeignCostRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SpecialProductForeignCostRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SpecialProductForeignCostDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SpecialProductForeignCostDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SpecialProductForeignCostRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SpecialProductForeignCostRow )
                return new SpecialProductForeignCostDao( (SpecialProductForeignCostRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SpecialProductForeignCostRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SpecialProductForeignCostRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g 
    */
    protected SpecialProductForeignCostDao( SpecialProductForeignCostRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SpecialProductForeignCostRow getSpecialProductForeignCostRow() {
        return row;
    }


  /** 
   * �w���{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g����{@@link SpecialProductForeignCostDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SpecialProductForeignCostRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SpecialProductForeignCostDao}�擾�̂��߂Ɏw���{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SpecialProductForeignCostDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SpecialProductForeignCostDao forRow( SpecialProductForeignCostRow row ) throws java.lang.IllegalArgumentException {
        return (SpecialProductForeignCostDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SpecialProductForeignCostRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SpecialProductForeignCostRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SpecialProductForeignCostPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SpecialProductForeignCostParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SpecialProductForeignCostRow.TYPE );
    }


  /** 
   * {@@link SpecialProductForeignCostRow}����ӂɓ��肷��{@@link SpecialProductForeignCostPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SpecialProductForeignCostRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SpecialProductForeignCostParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SpecialProductForeignCostPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SpecialProductForeignCostPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_offshoreProductCode �����Ώۂł���p_offshoreProductCode�t�B�[���h�̒l
   * @@param p_costDiv �����Ώۂł���p_costDiv�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * @@param p_amountFrom �����Ώۂł���p_amountFrom�t�B�[���h�̒l
   * @@param p_sideDiv �����Ώۂł���p_sideDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SpecialProductForeignCostRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SpecialProductForeignCostRow findRowByPk( String p_institutionCode, String p_marketCode, long p_productId, String p_offshoreProductCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SpecialProductForeignCostPK pk = new SpecialProductForeignCostPK( p_institutionCode, p_marketCode, p_productId, p_offshoreProductCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���SpecialProductForeignCostPK�I�u�W�F�N�g����{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SpecialProductForeignCostPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SpecialProductForeignCostRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SpecialProductForeignCostRow findRowByPk( SpecialProductForeignCostPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SpecialProductForeignCostRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,long,String,String,java.sql.Timestamp,double,String)}�����{@@link #forRow(SpecialProductForeignCostRow)}���g�p���Ă��������B 
   */
    public static SpecialProductForeignCostDao findDaoByPk( String p_institutionCode, String p_marketCode, long p_productId, String p_offshoreProductCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SpecialProductForeignCostPK pk = new SpecialProductForeignCostPK( p_institutionCode, p_marketCode, p_productId, p_offshoreProductCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv );
        SpecialProductForeignCostRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SpecialProductForeignCostPK)}�����{@@link #forRow(SpecialProductForeignCostRow)}���g�p���Ă��������B 
   */
    public static SpecialProductForeignCostDao findDaoByPk( SpecialProductForeignCostPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SpecialProductForeignCostRow row = findRowByPk( pk );
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
   * p_institutionCode, p_marketCode, p_productId, p_offshoreProductCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv, and �ɂĎw��̒l�����ӂ�{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_offshoreProductCode �����Ώۂł���p_offshoreProductCode�t�B�[���h�̒l
   * @@param p_costDiv �����Ώۂł���p_costDiv�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * @@param p_amountFrom �����Ώۂł���p_amountFrom�t�B�[���h�̒l
   * @@param p_sideDiv �����Ώۂł���p_sideDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_marketCode, p_productId, p_offshoreProductCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv, and �̒l�ƈ�v����{@@link SpecialProductForeignCostRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SpecialProductForeignCostRow findRowByInstitutionCodeMarketCodeProductIdOffshoreProductCodeCostDivAppliStartDateAmountFromSideDiv( String p_institutionCode, String p_marketCode, long p_productId, String p_offshoreProductCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SpecialProductForeignCostRow.TYPE,
            "institution_code=? and market_code=? and product_id=? and offshore_product_code=? and cost_div=? and appli_start_date=? and amount_from=? and side_div=?",
            null,
            new Object[] { p_institutionCode, p_marketCode, new Long(p_productId), p_offshoreProductCode, p_costDiv, p_appliStartDate, new Double(p_amountFrom), p_sideDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SpecialProductForeignCostRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SpecialProductForeignCostDao.findRowsByInstitutionCodeMarketCodeProductIdOffshoreProductCodeCostDivAppliStartDateAmountFromSideDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeMarketCodeProductIdOffshoreProductCodeCostDivAppliStartDateAmountFromSideDiv(String, String, long, String, String, java.sql.Timestamp, double, String)}�����{@@link #forRow(SpecialProductForeignCostRow)}���g�p���Ă��������B 
   */
    public static SpecialProductForeignCostDao findDaoByInstitutionCodeMarketCodeProductIdOffshoreProductCodeCostDivAppliStartDateAmountFromSideDiv( String p_institutionCode, String p_marketCode, long p_productId, String p_offshoreProductCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCodeProductIdOffshoreProductCodeCostDivAppliStartDateAmountFromSideDiv( p_institutionCode, p_marketCode, p_productId, p_offshoreProductCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
