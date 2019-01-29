head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsAccountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rlsgateway.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RlsAccountDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RlsAccountRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RlsAccountPK 
 * @@see RlsAccountRow 
 */
public class RlsAccountDao extends DataAccessObject {


  /** 
   * ����{@@link RlsAccountDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RlsAccountRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RlsAccountRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RlsAccountDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RlsAccountDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RlsAccountRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsAccountRow )
                return new RlsAccountDao( (RlsAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsAccountRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RlsAccountRow}�I�u�W�F�N�g 
    */
    protected RlsAccountDao( RlsAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RlsAccountRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RlsAccountRow getRlsAccountRow() {
        return row;
    }


  /** 
   * �w���{@@link RlsAccountRow}�I�u�W�F�N�g����{@@link RlsAccountDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RlsAccountRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RlsAccountDao}�擾�̂��߂Ɏw���{@@link RlsAccountRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RlsAccountDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RlsAccountDao forRow( RlsAccountRow row ) throws java.lang.IllegalArgumentException {
        return (RlsAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsAccountRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RlsAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RlsAccountPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RlsAccountParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsAccountRow.TYPE );
    }


  /** 
   * {@@link RlsAccountRow}����ӂɓ��肷��{@@link RlsAccountPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RlsAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RlsAccountParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RlsAccountPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RlsAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RlsAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_id �����Ώۂł���p_id�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsAccountRow findRowByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsAccountPK pk = new RlsAccountPK( p_id );
        return findRowByPk( pk );
    }


  /** 
   * �w���RlsAccountPK�I�u�W�F�N�g����{@@link RlsAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RlsAccountPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RlsAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RlsAccountRow findRowByPk( RlsAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(RlsAccountRow)}���g�p���Ă��������B 
   */
    public static RlsAccountDao findDaoByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsAccountPK pk = new RlsAccountPK( p_id );
        RlsAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RlsAccountPK)}�����{@@link #forRow(RlsAccountRow)}���g�p���Ă��������B 
   */
    public static RlsAccountDao findDaoByPk( RlsAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsAccountRow row = findRowByPk( pk );
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
   * p_id, and �ɂĎw��̒l�����ӂ�{@@link RlsAccountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_id �����Ώۂł���p_id�t�B�[���h�̒l
   * 
   * @@return �����w���p_id, and �̒l�ƈ�v����{@@link RlsAccountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RlsAccountRow findRowById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsAccountRow.TYPE,
            "id=?",
            null,
            new Object[] { new Long(p_id) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsAccountDao.findRowsById(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowById(long)}�����{@@link #forRow(RlsAccountRow)}���g�p���Ă��������B 
   */
    public static RlsAccountDao findDaoById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowById( p_id ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
