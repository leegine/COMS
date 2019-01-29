head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointConvertMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.point.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link PointConvertMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link PointConvertMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see PointConvertMasterPK 
 * @@see PointConvertMasterRow 
 */
public class PointConvertMasterDao extends DataAccessObject {


  /** 
   * ����{@@link PointConvertMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private PointConvertMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link PointConvertMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link PointConvertMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link PointConvertMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link PointConvertMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointConvertMasterRow )
                return new PointConvertMasterDao( (PointConvertMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointConvertMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointConvertMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link PointConvertMasterRow}�I�u�W�F�N�g 
    */
    protected PointConvertMasterDao( PointConvertMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link PointConvertMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public PointConvertMasterRow getPointConvertMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link PointConvertMasterRow}�I�u�W�F�N�g����{@@link PointConvertMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link PointConvertMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link PointConvertMasterDao}�擾�̂��߂Ɏw���{@@link PointConvertMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link PointConvertMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static PointConvertMasterDao forRow( PointConvertMasterRow row ) throws java.lang.IllegalArgumentException {
        return (PointConvertMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointConvertMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link PointConvertMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link PointConvertMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link PointConvertMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointConvertMasterRow.TYPE );
    }


  /** 
   * {@@link PointConvertMasterRow}����ӂɓ��肷��{@@link PointConvertMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link PointConvertMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link PointConvertMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link PointConvertMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static PointConvertMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link PointConvertMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_fundType �����Ώۂł���p_fundType�t�B�[���h�̒l
   * @@param p_tranType �����Ώۂł���p_tranType�t�B�[���h�̒l
   * @@param p_buySellDiv �����Ώۂł���p_buySellDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PointConvertMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PointConvertMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        PointConvertMasterPK pk = new PointConvertMasterPK( p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���PointConvertMasterPK�I�u�W�F�N�g����{@@link PointConvertMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����PointConvertMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PointConvertMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PointConvertMasterRow findRowByPk( PointConvertMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointConvertMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(PointConvertMasterRow)}���g�p���Ă��������B 
   */
    public static PointConvertMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        PointConvertMasterPK pk = new PointConvertMasterPK( p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv );
        PointConvertMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(PointConvertMasterPK)}�����{@@link #forRow(PointConvertMasterRow)}���g�p���Ă��������B 
   */
    public static PointConvertMasterDao findDaoByPk( PointConvertMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointConvertMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv, and �ɂĎw��̒l�����ӂ�{@@link PointConvertMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_fundType �����Ώۂł���p_fundType�t�B�[���h�̒l
   * @@param p_tranType �����Ώۂł���p_tranType�t�B�[���h�̒l
   * @@param p_buySellDiv �����Ώۂł���p_buySellDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv, and �̒l�ƈ�v����{@@link PointConvertMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static PointConvertMasterRow findRowByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointConvertMasterRow.TYPE,
            "institution_code=? and branch_code=? and fund_type=? and tran_type=? and buy_sell_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointConvertMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointConvertMasterDao.findRowsByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv(String, String, String, String, String)}�����{@@link #forRow(PointConvertMasterRow)}���g�p���Ă��������B 
   */
    public static PointConvertMasterDao findDaoByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv( p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
