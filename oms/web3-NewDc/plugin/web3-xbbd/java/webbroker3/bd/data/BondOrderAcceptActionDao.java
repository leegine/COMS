head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondOrderAcceptActionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link BondOrderAcceptActionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BondOrderAcceptActionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BondOrderAcceptActionPK 
 * @@see BondOrderAcceptActionRow 
 */
public class BondOrderAcceptActionDao extends DataAccessObject {


  /** 
   * ����{@@link BondOrderAcceptActionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BondOrderAcceptActionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BondOrderAcceptActionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BondOrderAcceptActionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BondOrderAcceptActionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BondOrderAcceptActionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondOrderAcceptActionRow )
                return new BondOrderAcceptActionDao( (BondOrderAcceptActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondOrderAcceptActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondOrderAcceptActionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g 
    */
    protected BondOrderAcceptActionDao( BondOrderAcceptActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BondOrderAcceptActionRow getBondOrderAcceptActionRow() {
        return row;
    }


  /** 
   * �w���{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g����{@@link BondOrderAcceptActionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BondOrderAcceptActionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BondOrderAcceptActionDao}�擾�̂��߂Ɏw���{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BondOrderAcceptActionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BondOrderAcceptActionDao forRow( BondOrderAcceptActionRow row ) throws java.lang.IllegalArgumentException {
        return (BondOrderAcceptActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondOrderAcceptActionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BondOrderAcceptActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BondOrderAcceptActionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BondOrderAcceptActionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondOrderAcceptActionRow.TYPE );
    }


  /** 
   * {@@link BondOrderAcceptActionRow}����ӂɓ��肷��{@@link BondOrderAcceptActionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BondOrderAcceptActionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BondOrderAcceptActionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BondOrderAcceptActionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BondOrderAcceptActionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderAcceptDate �����Ώۂł���p_orderAcceptDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondOrderAcceptActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondOrderAcceptActionRow findRowByPk( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderAcceptActionPK pk = new BondOrderAcceptActionPK( p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���BondOrderAcceptActionPK�I�u�W�F�N�g����{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BondOrderAcceptActionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondOrderAcceptActionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondOrderAcceptActionRow findRowByPk( BondOrderAcceptActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondOrderAcceptActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,String,java.sql.Timestamp)}�����{@@link #forRow(BondOrderAcceptActionRow)}���g�p���Ă��������B 
   */
    public static BondOrderAcceptActionDao findDaoByPk( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderAcceptActionPK pk = new BondOrderAcceptActionPK( p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate );
        BondOrderAcceptActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BondOrderAcceptActionPK)}�����{@@link #forRow(BondOrderAcceptActionRow)}���g�p���Ă��������B 
   */
    public static BondOrderAcceptActionDao findDaoByPk( BondOrderAcceptActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderAcceptActionRow row = findRowByPk( pk );
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
   * p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate, and �ɂĎw��̒l�����ӂ�{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderAcceptDate �����Ώۂł���p_orderAcceptDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate, and �̒l�ƈ�v����{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BondOrderAcceptActionRow findRowByProductIdInstitutionCodeBranchCodeOrderAcceptDate( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondOrderAcceptActionRow.TYPE,
            "product_id=? and institution_code=? and branch_code=? and order_accept_date=?",
            null,
            new Object[] { new Long(p_productId), p_institutionCode, p_branchCode, p_orderAcceptDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondOrderAcceptActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondOrderAcceptActionDao.findRowsByProductIdInstitutionCodeBranchCodeOrderAcceptDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByProductIdInstitutionCodeBranchCodeOrderAcceptDate(long, String, String, java.sql.Timestamp)}�����{@@link #forRow(BondOrderAcceptActionRow)}���g�p���Ă��������B 
   */
    public static BondOrderAcceptActionDao findDaoByProductIdInstitutionCodeBranchCodeOrderAcceptDate( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdInstitutionCodeBranchCodeOrderAcceptDate( p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_productId, p_institutionCode, p_branchCode, and �ɂĎw��̒l�Ɉ�v����{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, p_institutionCode, p_branchCode, and �̒l�ƈ�v����{@@link BondOrderAcceptActionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductIdInstitutionCodeBranchCode( long p_productId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderAcceptActionRow.TYPE,
            "product_id=? and institution_code=? and branch_code=?",
            null,
            new Object[] { new Long(p_productId), p_institutionCode, p_branchCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductIdInstitutionCodeBranchCode(long, String, String)}�����{@@link #forRow(BondOrderAcceptActionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductIdInstitutionCodeBranchCode( long p_productId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductIdInstitutionCodeBranchCode( p_productId, p_institutionCode, p_branchCode ) );
    }

}
@
