head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushIfoContDao.java;


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
 * {@@link QtpRichPushIfoContDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link QtpRichPushIfoContRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see QtpRichPushIfoContPK 
 * @@see QtpRichPushIfoContRow 
 */
public class QtpRichPushIfoContDao extends DataAccessObject {


  /** 
   * ����{@@link QtpRichPushIfoContDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private QtpRichPushIfoContRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link QtpRichPushIfoContRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link QtpRichPushIfoContDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link QtpRichPushIfoContDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link QtpRichPushIfoContRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushIfoContRow )
                return new QtpRichPushIfoContDao( (QtpRichPushIfoContRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushIfoContRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushIfoContRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link QtpRichPushIfoContRow}�I�u�W�F�N�g 
    */
    protected QtpRichPushIfoContDao( QtpRichPushIfoContRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link QtpRichPushIfoContRow}�I�u�W�F�N�g���擾���܂��B
   */
    public QtpRichPushIfoContRow getQtpRichPushIfoContRow() {
        return row;
    }


  /** 
   * �w���{@@link QtpRichPushIfoContRow}�I�u�W�F�N�g����{@@link QtpRichPushIfoContDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link QtpRichPushIfoContRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link QtpRichPushIfoContDao}�擾�̂��߂Ɏw���{@@link QtpRichPushIfoContRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link QtpRichPushIfoContDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static QtpRichPushIfoContDao forRow( QtpRichPushIfoContRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushIfoContDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushIfoContRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link QtpRichPushIfoContRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link QtpRichPushIfoContPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link QtpRichPushIfoContParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushIfoContRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushIfoContRow}����ӂɓ��肷��{@@link QtpRichPushIfoContPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link QtpRichPushIfoContRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link QtpRichPushIfoContParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link QtpRichPushIfoContPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static QtpRichPushIfoContPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushIfoContPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link QtpRichPushIfoContRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_serlnum �����Ώۂł���p_serlnum�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QtpRichPushIfoContRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QtpRichPushIfoContRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoContPK pk = new QtpRichPushIfoContPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * �w���QtpRichPushIfoContPK�I�u�W�F�N�g����{@@link QtpRichPushIfoContRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����QtpRichPushIfoContPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QtpRichPushIfoContRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QtpRichPushIfoContRow findRowByPk( QtpRichPushIfoContPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushIfoContRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(QtpRichPushIfoContRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushIfoContDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoContPK pk = new QtpRichPushIfoContPK( p_serlnum );
        QtpRichPushIfoContRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(QtpRichPushIfoContPK)}�����{@@link #forRow(QtpRichPushIfoContRow)}���g�p���Ă��������B 
   */
    public static QtpRichPushIfoContDao findDaoByPk( QtpRichPushIfoContPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoContRow row = findRowByPk( pk );
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
