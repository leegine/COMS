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
filename	BondBranchConditionDao.java;


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
 * {@@link BondBranchConditionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BondBranchConditionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BondBranchConditionPK 
 * @@see BondBranchConditionRow 
 */
public class BondBranchConditionDao extends DataAccessObject {


  /** 
   * ����{@@link BondBranchConditionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BondBranchConditionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BondBranchConditionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BondBranchConditionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BondBranchConditionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BondBranchConditionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondBranchConditionRow )
                return new BondBranchConditionDao( (BondBranchConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondBranchConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondBranchConditionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BondBranchConditionRow}�I�u�W�F�N�g 
    */
    protected BondBranchConditionDao( BondBranchConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BondBranchConditionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BondBranchConditionRow getBondBranchConditionRow() {
        return row;
    }


  /** 
   * �w���{@@link BondBranchConditionRow}�I�u�W�F�N�g����{@@link BondBranchConditionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BondBranchConditionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BondBranchConditionDao}�擾�̂��߂Ɏw���{@@link BondBranchConditionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BondBranchConditionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BondBranchConditionDao forRow( BondBranchConditionRow row ) throws java.lang.IllegalArgumentException {
        return (BondBranchConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondBranchConditionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BondBranchConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BondBranchConditionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BondBranchConditionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondBranchConditionRow.TYPE );
    }


  /** 
   * {@@link BondBranchConditionRow}����ӂɓ��肷��{@@link BondBranchConditionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BondBranchConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BondBranchConditionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BondBranchConditionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BondBranchConditionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BondBranchConditionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BondBranchConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondBranchConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondBranchConditionRow findRowByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchConditionPK pk = new BondBranchConditionPK( p_branchId );
        return findRowByPk( pk );
    }


  /** 
   * �w���BondBranchConditionPK�I�u�W�F�N�g����{@@link BondBranchConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BondBranchConditionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BondBranchConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BondBranchConditionRow findRowByPk( BondBranchConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondBranchConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(BondBranchConditionRow)}���g�p���Ă��������B 
   */
    public static BondBranchConditionDao findDaoByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchConditionPK pk = new BondBranchConditionPK( p_branchId );
        BondBranchConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BondBranchConditionPK)}�����{@@link #forRow(BondBranchConditionRow)}���g�p���Ă��������B 
   */
    public static BondBranchConditionDao findDaoByPk( BondBranchConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchConditionRow row = findRowByPk( pk );
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
   * p_branchId, and �ɂĎw��̒l�����ӂ�{@@link BondBranchConditionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �����w���p_branchId, and �̒l�ƈ�v����{@@link BondBranchConditionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BondBranchConditionRow findRowByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondBranchConditionRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondBranchConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondBranchConditionDao.findRowsByBranchId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBranchId(long)}�����{@@link #forRow(BondBranchConditionRow)}���g�p���Ă��������B 
   */
    public static BondBranchConditionDao findDaoByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchId( p_branchId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
