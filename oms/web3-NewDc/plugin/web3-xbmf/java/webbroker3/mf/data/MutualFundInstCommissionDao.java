head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCommissionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFundInstCommissionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MutualFundInstCommissionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MutualFundInstCommissionPK 
 * @@see MutualFundInstCommissionRow 
 */
public class MutualFundInstCommissionDao extends DataAccessObject {


  /** 
   * ����{@@link MutualFundInstCommissionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MutualFundInstCommissionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MutualFundInstCommissionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MutualFundInstCommissionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MutualFundInstCommissionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MutualFundInstCommissionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundInstCommissionRow )
                return new MutualFundInstCommissionDao( (MutualFundInstCommissionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundInstCommissionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundInstCommissionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g 
    */
    protected MutualFundInstCommissionDao( MutualFundInstCommissionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MutualFundInstCommissionRow getMutualFundInstCommissionRow() {
        return row;
    }


  /** 
   * �w���{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g����{@@link MutualFundInstCommissionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MutualFundInstCommissionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MutualFundInstCommissionDao}�擾�̂��߂Ɏw���{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MutualFundInstCommissionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MutualFundInstCommissionDao forRow( MutualFundInstCommissionRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundInstCommissionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundInstCommissionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MutualFundInstCommissionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MutualFundInstCommissionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MutualFundInstCommissionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundInstCommissionRow.TYPE );
    }


  /** 
   * {@@link MutualFundInstCommissionRow}����ӂɓ��肷��{@@link MutualFundInstCommissionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MutualFundInstCommissionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MutualFundInstCommissionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MutualFundInstCommissionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MutualFundInstCommissionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_dealDiv �����Ώۂł���p_dealDiv�t�B�[���h�̒l
   * @@param p_orderChanel �����Ώۂł���p_orderChanel�t�B�[���h�̒l
   * @@param p_validDateFrom �����Ώۂł���p_validDateFrom�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MutualFundInstCommissionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MutualFundInstCommissionRow findRowByPk( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCommissionPK pk = new MutualFundInstCommissionPK( p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom );
        return findRowByPk( pk );
    }


  /** 
   * �w���MutualFundInstCommissionPK�I�u�W�F�N�g����{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MutualFundInstCommissionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MutualFundInstCommissionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MutualFundInstCommissionRow findRowByPk( MutualFundInstCommissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundInstCommissionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,java.sql.Timestamp)}�����{@@link #forRow(MutualFundInstCommissionRow)}���g�p���Ă��������B 
   */
    public static MutualFundInstCommissionDao findDaoByPk( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCommissionPK pk = new MutualFundInstCommissionPK( p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom );
        MutualFundInstCommissionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MutualFundInstCommissionPK)}�����{@@link #forRow(MutualFundInstCommissionRow)}���g�p���Ă��������B 
   */
    public static MutualFundInstCommissionDao findDaoByPk( MutualFundInstCommissionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCommissionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom, and �ɂĎw��̒l�����ӂ�{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_dealDiv �����Ώۂł���p_dealDiv�t�B�[���h�̒l
   * @@param p_orderChanel �����Ώۂł���p_orderChanel�t�B�[���h�̒l
   * @@param p_validDateFrom �����Ώۂł���p_validDateFrom�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom, and �̒l�ƈ�v����{@@link MutualFundInstCommissionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MutualFundInstCommissionRow findRowByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFundInstCommissionRow.TYPE,
            "institution_code=? and product_code=? and deal_div=? and order_chanel=? and valid_date_from=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFundInstCommissionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFundInstCommissionDao.findRowsByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom(String, String, String, String, java.sql.Timestamp)}�����{@@link #forRow(MutualFundInstCommissionRow)}���g�p���Ă��������B 
   */
    public static MutualFundInstCommissionDao findDaoByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom( String p_institutionCode, String p_productCode, String p_dealDiv, String p_orderChanel, java.sql.Timestamp p_validDateFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeDealDivOrderChanelValidDateFrom( p_institutionCode, p_productCode, p_dealDiv, p_orderChanel, p_validDateFrom ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
