head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfSubAssetDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MfSubAssetDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MfSubAssetRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MfSubAssetPK 
 * @@see MfSubAssetRow 
 */
public class MfSubAssetDao extends DataAccessObject {


  /** 
   * ����{@@link MfSubAssetDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MfSubAssetRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MfSubAssetRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MfSubAssetDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MfSubAssetDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MfSubAssetRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfSubAssetRow )
                return new MfSubAssetDao( (MfSubAssetRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfSubAssetRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfSubAssetRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MfSubAssetRow}�I�u�W�F�N�g 
    */
    protected MfSubAssetDao( MfSubAssetRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MfSubAssetRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MfSubAssetRow getMfSubAssetRow() {
        return row;
    }


  /** 
   * �w���{@@link MfSubAssetRow}�I�u�W�F�N�g����{@@link MfSubAssetDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MfSubAssetRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MfSubAssetDao}�擾�̂��߂Ɏw���{@@link MfSubAssetRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MfSubAssetDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MfSubAssetDao forRow( MfSubAssetRow row ) throws java.lang.IllegalArgumentException {
        return (MfSubAssetDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfSubAssetRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MfSubAssetRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MfSubAssetPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MfSubAssetParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfSubAssetRow.TYPE );
    }


  /** 
   * {@@link MfSubAssetRow}����ӂɓ��肷��{@@link MfSubAssetPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MfSubAssetRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MfSubAssetParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MfSubAssetPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MfSubAssetPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MfSubAssetPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MfSubAssetRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_assetId �����Ώۂł���p_assetId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfSubAssetRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfSubAssetRow findRowByPk( long p_assetId ) throws DataFindException, DataQueryException, DataNetworkException {
        MfSubAssetPK pk = new MfSubAssetPK( p_assetId );
        return findRowByPk( pk );
    }


  /** 
   * �w���MfSubAssetPK�I�u�W�F�N�g����{@@link MfSubAssetRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MfSubAssetPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MfSubAssetRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MfSubAssetRow findRowByPk( MfSubAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfSubAssetRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(MfSubAssetRow)}���g�p���Ă��������B 
   */
    public static MfSubAssetDao findDaoByPk( long p_assetId ) throws DataFindException, DataQueryException, DataNetworkException {
        MfSubAssetPK pk = new MfSubAssetPK( p_assetId );
        MfSubAssetRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MfSubAssetPK)}�����{@@link #forRow(MfSubAssetRow)}���g�p���Ă��������B 
   */
    public static MfSubAssetDao findDaoByPk( MfSubAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfSubAssetRow row = findRowByPk( pk );
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
   * p_assetId, and �ɂĎw��̒l�����ӂ�{@@link MfSubAssetRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_assetId �����Ώۂł���p_assetId�t�B�[���h�̒l
   * 
   * @@return �����w���p_assetId, and �̒l�ƈ�v����{@@link MfSubAssetRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MfSubAssetRow findRowByAssetId( long p_assetId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfSubAssetRow.TYPE,
            "asset_id=?",
            null,
            new Object[] { new Long(p_assetId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfSubAssetRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfSubAssetDao.findRowsByAssetId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAssetId(long)}�����{@@link #forRow(MfSubAssetRow)}���g�p���Ă��������B 
   */
    public static MfSubAssetDao findDaoByAssetId( long p_assetId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAssetId( p_assetId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
