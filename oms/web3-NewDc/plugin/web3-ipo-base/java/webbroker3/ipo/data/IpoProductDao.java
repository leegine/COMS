head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoProductDao.java;


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
 * {@@link IpoProductDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IpoProductRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IpoProductPK 
 * @@see IpoProductRow 
 */
public class IpoProductDao extends DataAccessObject {


  /** 
   * ����{@@link IpoProductDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IpoProductRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IpoProductRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IpoProductDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IpoProductDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IpoProductRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IpoProductRow )
                return new IpoProductDao( (IpoProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IpoProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IpoProductRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IpoProductRow}�I�u�W�F�N�g 
    */
    protected IpoProductDao( IpoProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IpoProductRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IpoProductRow getIpoProductRow() {
        return row;
    }


  /** 
   * �w���{@@link IpoProductRow}�I�u�W�F�N�g����{@@link IpoProductDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IpoProductRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IpoProductDao}�擾�̂��߂Ɏw���{@@link IpoProductRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IpoProductDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IpoProductDao forRow( IpoProductRow row ) throws java.lang.IllegalArgumentException {
        return (IpoProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IpoProductRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IpoProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IpoProductPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IpoProductParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IpoProductRow.TYPE );
    }


  /** 
   * {@@link IpoProductRow}����ӂɓ��肷��{@@link IpoProductPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IpoProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IpoProductParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IpoProductPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IpoProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IpoProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IpoProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_ipoProductId �����Ώۂł���p_ipoProductId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IpoProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IpoProductRow findRowByPk( long p_ipoProductId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoProductPK pk = new IpoProductPK( p_ipoProductId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IpoProductPK�I�u�W�F�N�g����{@@link IpoProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IpoProductPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IpoProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IpoProductRow findRowByPk( IpoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IpoProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(IpoProductRow)}���g�p���Ă��������B 
   */
    public static IpoProductDao findDaoByPk( long p_ipoProductId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoProductPK pk = new IpoProductPK( p_ipoProductId );
        IpoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IpoProductPK)}�����{@@link #forRow(IpoProductRow)}���g�p���Ă��������B 
   */
    public static IpoProductDao findDaoByPk( IpoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoProductRow row = findRowByPk( pk );
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
   * p_ipoProductId, and �ɂĎw��̒l�����ӂ�{@@link IpoProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_ipoProductId �����Ώۂł���p_ipoProductId�t�B�[���h�̒l
   * 
   * @@return �����w���p_ipoProductId, and �̒l�ƈ�v����{@@link IpoProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IpoProductRow findRowByIpoProductId( long p_ipoProductId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoProductRow.TYPE,
            "ipo_product_id=?",
            null,
            new Object[] { new Long(p_ipoProductId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoProductDao.findRowsByIpoProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByIpoProductId(long)}�����{@@link #forRow(IpoProductRow)}���g�p���Ă��������B 
   */
    public static IpoProductDao findDaoByIpoProductId( long p_ipoProductId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByIpoProductId( p_ipoProductId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
