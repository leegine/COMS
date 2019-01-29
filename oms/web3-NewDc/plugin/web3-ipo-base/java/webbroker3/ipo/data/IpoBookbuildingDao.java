head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ipo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IpoBookbuildingDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IpoBookbuildingRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IpoBookbuildingPK 
 * @@see IpoBookbuildingRow 
 */
public class IpoBookbuildingDao extends DataAccessObject {


  /** 
   * ����{@@link IpoBookbuildingDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IpoBookbuildingRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IpoBookbuildingRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IpoBookbuildingDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IpoBookbuildingDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IpoBookbuildingRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IpoBookbuildingRow )
                return new IpoBookbuildingDao( (IpoBookbuildingRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IpoBookbuildingRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IpoBookbuildingRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IpoBookbuildingRow}�I�u�W�F�N�g 
    */
    protected IpoBookbuildingDao( IpoBookbuildingRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IpoBookbuildingRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IpoBookbuildingRow getIpoBookbuildingRow() {
        return row;
    }


  /** 
   * �w���{@@link IpoBookbuildingRow}�I�u�W�F�N�g����{@@link IpoBookbuildingDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IpoBookbuildingRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IpoBookbuildingDao}�擾�̂��߂Ɏw���{@@link IpoBookbuildingRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IpoBookbuildingDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IpoBookbuildingDao forRow( IpoBookbuildingRow row ) throws java.lang.IllegalArgumentException {
        return (IpoBookbuildingDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IpoBookbuildingRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IpoBookbuildingRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IpoBookbuildingPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IpoBookbuildingParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IpoBookbuildingRow.TYPE );
    }


  /** 
   * {@@link IpoBookbuildingRow}����ӂɓ��肷��{@@link IpoBookbuildingPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IpoBookbuildingRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IpoBookbuildingParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IpoBookbuildingPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IpoBookbuildingPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IpoBookbuildingRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_ipoProductId �����Ώۂł���p_ipoProductId�t�B�[���h�̒l
   * @@param p_bbDiv �����Ώۂł���p_bbDiv�t�B�[���h�̒l
   * @@param p_bbSeq �����Ώۂł���p_bbSeq�t�B�[���h�̒l
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IpoBookbuildingRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IpoBookbuildingRow findRowByPk( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingPK pk = new IpoBookbuildingPK( p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IpoBookbuildingPK�I�u�W�F�N�g����{@@link IpoBookbuildingRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IpoBookbuildingPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IpoBookbuildingRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IpoBookbuildingRow findRowByPk( IpoBookbuildingPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IpoBookbuildingRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,long,long)}�����{@@link #forRow(IpoBookbuildingRow)}���g�p���Ă��������B 
   */
    public static IpoBookbuildingDao findDaoByPk( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingPK pk = new IpoBookbuildingPK( p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId );
        IpoBookbuildingRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IpoBookbuildingPK)}�����{@@link #forRow(IpoBookbuildingRow)}���g�p���Ă��������B 
   */
    public static IpoBookbuildingDao findDaoByPk( IpoBookbuildingPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingRow row = findRowByPk( pk );
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
   * p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId, and �ɂĎw��̒l�����ӂ�{@@link IpoBookbuildingRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_ipoProductId �����Ώۂł���p_ipoProductId�t�B�[���h�̒l
   * @@param p_bbDiv �����Ώۂł���p_bbDiv�t�B�[���h�̒l
   * @@param p_bbSeq �����Ώۂł���p_bbSeq�t�B�[���h�̒l
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �����w���p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId, and �̒l�ƈ�v����{@@link IpoBookbuildingRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IpoBookbuildingRow findRowByIpoProductIdBbDivBbSeqBranchId( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoBookbuildingRow.TYPE,
            "ipo_product_id=? and bb_div=? and bb_seq=? and branch_id=?",
            null,
            new Object[] { new Long(p_ipoProductId), p_bbDiv, new Long(p_bbSeq), new Long(p_branchId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoBookbuildingRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoBookbuildingDao.findRowsByIpoProductIdBbDivBbSeqBranchId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByIpoProductIdBbDivBbSeqBranchId(long, String, long, long)}�����{@@link #forRow(IpoBookbuildingRow)}���g�p���Ă��������B 
   */
    public static IpoBookbuildingDao findDaoByIpoProductIdBbDivBbSeqBranchId( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByIpoProductIdBbDivBbSeqBranchId( p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
