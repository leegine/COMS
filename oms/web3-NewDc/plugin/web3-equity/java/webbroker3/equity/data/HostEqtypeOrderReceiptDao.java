head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeOrderReceiptDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostEqtypeOrderReceiptDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostEqtypeOrderReceiptRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostEqtypeOrderReceiptPK 
 * @@see HostEqtypeOrderReceiptRow 
 */
public class HostEqtypeOrderReceiptDao extends DataAccessObject {


  /** 
   * ����{@@link HostEqtypeOrderReceiptDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostEqtypeOrderReceiptRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostEqtypeOrderReceiptRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostEqtypeOrderReceiptDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostEqtypeOrderReceiptDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostEqtypeOrderReceiptRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeOrderReceiptRow )
                return new HostEqtypeOrderReceiptDao( (HostEqtypeOrderReceiptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeOrderReceiptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeOrderReceiptRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g 
    */
    protected HostEqtypeOrderReceiptDao( HostEqtypeOrderReceiptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostEqtypeOrderReceiptRow getHostEqtypeOrderReceiptRow() {
        return row;
    }


  /** 
   * �w���{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g����{@@link HostEqtypeOrderReceiptDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostEqtypeOrderReceiptRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostEqtypeOrderReceiptDao}�擾�̂��߂Ɏw���{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostEqtypeOrderReceiptDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostEqtypeOrderReceiptDao forRow( HostEqtypeOrderReceiptRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeOrderReceiptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeOrderReceiptRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostEqtypeOrderReceiptRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostEqtypeOrderReceiptPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostEqtypeOrderReceiptParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeOrderReceiptRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeOrderReceiptRow}����ӂɓ��肷��{@@link HostEqtypeOrderReceiptPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostEqtypeOrderReceiptRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostEqtypeOrderReceiptParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostEqtypeOrderReceiptPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostEqtypeOrderReceiptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostEqtypeOrderReceiptRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostEqtypeOrderReceiptRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderReceiptPK pk = new HostEqtypeOrderReceiptPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostEqtypeOrderReceiptPK�I�u�W�F�N�g����{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostEqtypeOrderReceiptPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostEqtypeOrderReceiptRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostEqtypeOrderReceiptRow findRowByPk( HostEqtypeOrderReceiptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeOrderReceiptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostEqtypeOrderReceiptRow)}���g�p���Ă��������B 
   */
    public static HostEqtypeOrderReceiptDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderReceiptPK pk = new HostEqtypeOrderReceiptPK( p_rowid );
        HostEqtypeOrderReceiptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostEqtypeOrderReceiptPK)}�����{@@link #forRow(HostEqtypeOrderReceiptRow)}���g�p���Ă��������B 
   */
    public static HostEqtypeOrderReceiptDao findDaoByPk( HostEqtypeOrderReceiptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderReceiptRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostEqtypeOrderReceiptRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderReceiptRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByOrderRequestNumber(String)}�����{@@link #forRow(HostEqtypeOrderReceiptRow)}���g�p���Ă��������B 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }

}
@