head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.29.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushEquityChangeCancelDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RichPushEquityChangeCancelDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RichPushEquityChangeCancelRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RichPushEquityChangeCancelPK 
 * @@see RichPushEquityChangeCancelRow 
 */
public class RichPushEquityChangeCancelDao extends DataAccessObject {


  /** 
   * ����{@@link RichPushEquityChangeCancelDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RichPushEquityChangeCancelRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RichPushEquityChangeCancelRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RichPushEquityChangeCancelDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RichPushEquityChangeCancelDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RichPushEquityChangeCancelRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RichPushEquityChangeCancelRow )
                return new RichPushEquityChangeCancelDao( (RichPushEquityChangeCancelRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RichPushEquityChangeCancelRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RichPushEquityChangeCancelRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RichPushEquityChangeCancelRow}�I�u�W�F�N�g 
    */
    protected RichPushEquityChangeCancelDao( RichPushEquityChangeCancelRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RichPushEquityChangeCancelRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RichPushEquityChangeCancelRow getRichPushEquityChangeCancelRow() {
        return row;
    }


  /** 
   * �w���{@@link RichPushEquityChangeCancelRow}�I�u�W�F�N�g����{@@link RichPushEquityChangeCancelDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RichPushEquityChangeCancelRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RichPushEquityChangeCancelDao}�擾�̂��߂Ɏw���{@@link RichPushEquityChangeCancelRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RichPushEquityChangeCancelDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RichPushEquityChangeCancelDao forRow( RichPushEquityChangeCancelRow row ) throws java.lang.IllegalArgumentException {
        return (RichPushEquityChangeCancelDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RichPushEquityChangeCancelRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RichPushEquityChangeCancelRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RichPushEquityChangeCancelPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RichPushEquityChangeCancelParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RichPushEquityChangeCancelRow.TYPE );
    }


  /** 
   * {@@link RichPushEquityChangeCancelRow}����ӂɓ��肷��{@@link RichPushEquityChangeCancelPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RichPushEquityChangeCancelRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RichPushEquityChangeCancelParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RichPushEquityChangeCancelPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RichPushEquityChangeCancelPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RichPushEquityChangeCancelPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RichPushEquityChangeCancelRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RichPushEquityChangeCancelRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RichPushEquityChangeCancelRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityChangeCancelPK pk = new RichPushEquityChangeCancelPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * �w���RichPushEquityChangeCancelPK�I�u�W�F�N�g����{@@link RichPushEquityChangeCancelRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RichPushEquityChangeCancelPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RichPushEquityChangeCancelRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RichPushEquityChangeCancelRow findRowByPk( RichPushEquityChangeCancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RichPushEquityChangeCancelRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RichPushEquityChangeCancelRow)}���g�p���Ă��������B 
   */
    public static RichPushEquityChangeCancelDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityChangeCancelPK pk = new RichPushEquityChangeCancelPK( p_serlnum );
        RichPushEquityChangeCancelRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RichPushEquityChangeCancelPK)}�����{@@link #forRow(RichPushEquityChangeCancelRow)}���g�p���Ă��������B 
   */
    public static RichPushEquityChangeCancelDao findDaoByPk( RichPushEquityChangeCancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityChangeCancelRow row = findRowByPk( pk );
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
