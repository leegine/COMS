head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushEquityLapseDao.java;


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
 * {@@link RichPushEquityLapseDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RichPushEquityLapseRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RichPushEquityLapsePK 
 * @@see RichPushEquityLapseRow 
 */
public class RichPushEquityLapseDao extends DataAccessObject {


  /** 
   * ����{@@link RichPushEquityLapseDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RichPushEquityLapseRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RichPushEquityLapseRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RichPushEquityLapseDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RichPushEquityLapseDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RichPushEquityLapseRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RichPushEquityLapseRow )
                return new RichPushEquityLapseDao( (RichPushEquityLapseRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RichPushEquityLapseRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RichPushEquityLapseRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RichPushEquityLapseRow}�I�u�W�F�N�g 
    */
    protected RichPushEquityLapseDao( RichPushEquityLapseRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RichPushEquityLapseRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RichPushEquityLapseRow getRichPushEquityLapseRow() {
        return row;
    }


  /** 
   * �w���{@@link RichPushEquityLapseRow}�I�u�W�F�N�g����{@@link RichPushEquityLapseDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RichPushEquityLapseRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RichPushEquityLapseDao}�擾�̂��߂Ɏw���{@@link RichPushEquityLapseRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RichPushEquityLapseDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RichPushEquityLapseDao forRow( RichPushEquityLapseRow row ) throws java.lang.IllegalArgumentException {
        return (RichPushEquityLapseDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RichPushEquityLapseRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RichPushEquityLapseRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RichPushEquityLapsePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RichPushEquityLapseParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RichPushEquityLapseRow.TYPE );
    }


  /** 
   * {@@link RichPushEquityLapseRow}����ӂɓ��肷��{@@link RichPushEquityLapsePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RichPushEquityLapseRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RichPushEquityLapseParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RichPushEquityLapsePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RichPushEquityLapsePK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RichPushEquityLapsePK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RichPushEquityLapseRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RichPushEquityLapseRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RichPushEquityLapseRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityLapsePK pk = new RichPushEquityLapsePK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * �w���RichPushEquityLapsePK�I�u�W�F�N�g����{@@link RichPushEquityLapseRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RichPushEquityLapsePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RichPushEquityLapseRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RichPushEquityLapseRow findRowByPk( RichPushEquityLapsePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RichPushEquityLapseRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RichPushEquityLapseRow)}���g�p���Ă��������B 
   */
    public static RichPushEquityLapseDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityLapsePK pk = new RichPushEquityLapsePK( p_serlnum );
        RichPushEquityLapseRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RichPushEquityLapsePK)}�����{@@link #forRow(RichPushEquityLapseRow)}���g�p���Ă��������B 
   */
    public static RichPushEquityLapseDao findDaoByPk( RichPushEquityLapsePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityLapseRow row = findRowByPk( pk );
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
