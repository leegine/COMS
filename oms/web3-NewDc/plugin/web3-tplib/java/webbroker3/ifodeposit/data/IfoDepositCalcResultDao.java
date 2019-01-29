head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcResultDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifodeposit.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IfoDepositCalcResultDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoDepositCalcResultRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoDepositCalcResultPK 
 * @@see IfoDepositCalcResultRow 
 */
public class IfoDepositCalcResultDao extends DataAccessObject {


  /** 
   * ����{@@link IfoDepositCalcResultDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoDepositCalcResultRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoDepositCalcResultRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoDepositCalcResultDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoDepositCalcResultDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoDepositCalcResultRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoDepositCalcResultRow )
                return new IfoDepositCalcResultDao( (IfoDepositCalcResultRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoDepositCalcResultRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoDepositCalcResultRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoDepositCalcResultRow}�I�u�W�F�N�g 
    */
    protected IfoDepositCalcResultDao( IfoDepositCalcResultRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoDepositCalcResultRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoDepositCalcResultRow getIfoDepositCalcResultRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoDepositCalcResultRow}�I�u�W�F�N�g����{@@link IfoDepositCalcResultDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoDepositCalcResultRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoDepositCalcResultDao}�擾�̂��߂Ɏw���{@@link IfoDepositCalcResultRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoDepositCalcResultDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoDepositCalcResultDao forRow( IfoDepositCalcResultRow row ) throws java.lang.IllegalArgumentException {
        return (IfoDepositCalcResultDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoDepositCalcResultRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoDepositCalcResultRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoDepositCalcResultPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoDepositCalcResultParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoDepositCalcResultRow.TYPE );
    }


  /** 
   * {@@link IfoDepositCalcResultRow}����ӂɓ��肷��{@@link IfoDepositCalcResultPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoDepositCalcResultRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoDepositCalcResultParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoDepositCalcResultPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoDepositCalcResultPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoDepositCalcResultPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoDepositCalcResultRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_depositInfoId �����Ώۂł���p_depositInfoId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoDepositCalcResultRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoDepositCalcResultRow findRowByPk( long p_depositInfoId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcResultPK pk = new IfoDepositCalcResultPK( p_depositInfoId );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoDepositCalcResultPK�I�u�W�F�N�g����{@@link IfoDepositCalcResultRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoDepositCalcResultPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoDepositCalcResultRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoDepositCalcResultRow findRowByPk( IfoDepositCalcResultPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoDepositCalcResultRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(IfoDepositCalcResultRow)}���g�p���Ă��������B 
   */
    public static IfoDepositCalcResultDao findDaoByPk( long p_depositInfoId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcResultPK pk = new IfoDepositCalcResultPK( p_depositInfoId );
        IfoDepositCalcResultRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoDepositCalcResultPK)}�����{@@link #forRow(IfoDepositCalcResultRow)}���g�p���Ă��������B 
   */
    public static IfoDepositCalcResultDao findDaoByPk( IfoDepositCalcResultPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcResultRow row = findRowByPk( pk );
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
