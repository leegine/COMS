head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.25.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushIfoLapseDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.qtp.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link QtpRichPushIfoLapseDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link QtpRichPushIfoLapseRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see QtpRichPushIfoLapsePK 
 * @@see QtpRichPushIfoLapseRow 
 */
public class QtpRichPushIfoLapseDao extends DataAccessObject {


  /** 
   * ����{@@link QtpRichPushIfoLapseDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private QtpRichPushIfoLapseRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link QtpRichPushIfoLapseRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link QtpRichPushIfoLapseDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link QtpRichPushIfoLapseDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link QtpRichPushIfoLapseRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushIfoLapseRow )
                return new QtpRichPushIfoLapseDao( (QtpRichPushIfoLapseRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushIfoLapseRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushIfoLapseRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link QtpRichPushIfoLapseRow}�I�u�W�F�N�g 
    */
    protected QtpRichPushIfoLapseDao( QtpRichPushIfoLapseRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link QtpRichPushIfoLapseRow}�I�u�W�F�N�g���擾���܂��B
   */
    public QtpRichPushIfoLapseRow getQtpRichPushIfoLapseRow() {
        return row;
    }


  /** 
   * �w���{@@link QtpRichPushIfoLapseRow}�I�u�W�F�N�g����{@@link QtpRichPushIfoLapseDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link QtpRichPushIfoLapseRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link QtpRichPushIfoLapseDao}�擾�̂��߂Ɏw���{@@link QtpRichPushIfoLapseRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link QtpRichPushIfoLapseDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static QtpRichPushIfoLapseDao forRow( QtpRichPushIfoLapseRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushIfoLapseDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushIfoLapseRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link QtpRichPushIfoLapseRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link QtpRichPushIfoLapsePK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link QtpRichPushIfoLapseParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushIfoLapseRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushIfoLapseRow}����ӂɓ��肷��{@@link QtpRichPushIfoLapsePK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link QtpRichPushIfoLapseRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link QtpRichPushIfoLapseParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link QtpRichPushIfoLapsePK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static QtpRichPushIfoLapsePK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushIfoLapsePK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link QtpRichPushIfoLapseRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QtpRichPushIfoLapseRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QtpRichPushIfoLapseRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoLapsePK pk = new QtpRichPushIfoLapsePK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * �w���QtpRichPushIfoLapsePK�I�u�W�F�N�g����{@@link QtpRichPushIfoLapseRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����QtpRichPushIfoLapsePK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QtpRichPushIfoLapseRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QtpRichPushIfoLapseRow findRowByPk( QtpRichPushIfoLapsePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushIfoLapseRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(QtpRichPushIfoLapseRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushIfoLapseDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoLapsePK pk = new QtpRichPushIfoLapsePK( p_serlnum );
        QtpRichPushIfoLapseRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(QtpRichPushIfoLapsePK)}�����{@@link #forRow(QtpRichPushIfoLapseRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushIfoLapseDao findDaoByPk( QtpRichPushIfoLapsePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoLapseRow row = findRowByPk( pk );
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
