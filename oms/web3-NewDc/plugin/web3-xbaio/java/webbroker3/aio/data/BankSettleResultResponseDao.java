head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.42.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankSettleResultResponseDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link BankSettleResultResponseDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BankSettleResultResponseRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BankSettleResultResponsePK 
 * @@see BankSettleResultResponseRow 
 */
public class BankSettleResultResponseDao extends DataAccessObject {


  /** 
   * ����{@@link BankSettleResultResponseDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BankSettleResultResponseRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BankSettleResultResponseRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BankSettleResultResponseDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BankSettleResultResponseDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BankSettleResultResponseRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankSettleResultResponseRow )
                return new BankSettleResultResponseDao( (BankSettleResultResponseRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankSettleResultResponseRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankSettleResultResponseRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BankSettleResultResponseRow}�I�u�W�F�N�g 
    */
    protected BankSettleResultResponseDao( BankSettleResultResponseRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BankSettleResultResponseRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BankSettleResultResponseRow getBankSettleResultResponseRow() {
        return row;
    }


  /** 
   * �w���{@@link BankSettleResultResponseRow}�I�u�W�F�N�g����{@@link BankSettleResultResponseDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BankSettleResultResponseRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BankSettleResultResponseDao}�擾�̂��߂Ɏw���{@@link BankSettleResultResponseRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BankSettleResultResponseDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BankSettleResultResponseDao forRow( BankSettleResultResponseRow row ) throws java.lang.IllegalArgumentException {
        return (BankSettleResultResponseDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankSettleResultResponseRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BankSettleResultResponseRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BankSettleResultResponsePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BankSettleResultResponseParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankSettleResultResponseRow.TYPE );
    }


  /** 
   * {@@link BankSettleResultResponseRow}����ӂɓ��肷��{@@link BankSettleResultResponsePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BankSettleResultResponseRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BankSettleResultResponseParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BankSettleResultResponsePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BankSettleResultResponsePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BankSettleResultResponseRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankSettleResultResponseRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankSettleResultResponseRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleResultResponsePK pk = new BankSettleResultResponsePK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���BankSettleResultResponsePK�I�u�W�F�N�g����{@@link BankSettleResultResponseRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BankSettleResultResponsePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BankSettleResultResponseRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BankSettleResultResponseRow findRowByPk( BankSettleResultResponsePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankSettleResultResponseRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(BankSettleResultResponseRow)}���g�p���Ă��������B 
   */
    public static BankSettleResultResponseDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleResultResponsePK pk = new BankSettleResultResponsePK( p_rowid );
        BankSettleResultResponseRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BankSettleResultResponsePK)}�����{@@link #forRow(BankSettleResultResponseRow)}���g�p���Ă��������B 
   */
    public static BankSettleResultResponseDao findDaoByPk( BankSettleResultResponsePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleResultResponseRow row = findRowByPk( pk );
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

        // (none) 

}
@
