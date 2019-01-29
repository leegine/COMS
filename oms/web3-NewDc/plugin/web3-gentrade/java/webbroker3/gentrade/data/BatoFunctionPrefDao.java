head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoFunctionPrefDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BatoFunctionPrefDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BatoFunctionPrefRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BatoFunctionPrefPK 
 * @@see BatoFunctionPrefRow 
 */
public class BatoFunctionPrefDao extends DataAccessObject {


  /** 
   * ����{@@link BatoFunctionPrefDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BatoFunctionPrefRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BatoFunctionPrefRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BatoFunctionPrefDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BatoFunctionPrefDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BatoFunctionPrefRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatoFunctionPrefRow )
                return new BatoFunctionPrefDao( (BatoFunctionPrefRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatoFunctionPrefRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatoFunctionPrefRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BatoFunctionPrefRow}�I�u�W�F�N�g 
    */
    protected BatoFunctionPrefDao( BatoFunctionPrefRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BatoFunctionPrefRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BatoFunctionPrefRow getBatoFunctionPrefRow() {
        return row;
    }


  /** 
   * �w���{@@link BatoFunctionPrefRow}�I�u�W�F�N�g����{@@link BatoFunctionPrefDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BatoFunctionPrefRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BatoFunctionPrefDao}�擾�̂��߂Ɏw���{@@link BatoFunctionPrefRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BatoFunctionPrefDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BatoFunctionPrefDao forRow( BatoFunctionPrefRow row ) throws java.lang.IllegalArgumentException {
        return (BatoFunctionPrefDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatoFunctionPrefRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BatoFunctionPrefRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BatoFunctionPrefPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BatoFunctionPrefParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatoFunctionPrefRow.TYPE );
    }


  /** 
   * {@@link BatoFunctionPrefRow}����ӂɓ��肷��{@@link BatoFunctionPrefPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BatoFunctionPrefRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BatoFunctionPrefParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BatoFunctionPrefPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BatoFunctionPrefPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BatoFunctionPrefRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_functionDiv �����Ώۂł���p_functionDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BatoFunctionPrefRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BatoFunctionPrefRow findRowByPk( String p_functionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoFunctionPrefPK pk = new BatoFunctionPrefPK( p_functionDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���BatoFunctionPrefPK�I�u�W�F�N�g����{@@link BatoFunctionPrefRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BatoFunctionPrefPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BatoFunctionPrefRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BatoFunctionPrefRow findRowByPk( BatoFunctionPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatoFunctionPrefRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(BatoFunctionPrefRow)}���g�p���Ă��������B 
   */
    public static BatoFunctionPrefDao findDaoByPk( String p_functionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoFunctionPrefPK pk = new BatoFunctionPrefPK( p_functionDiv );
        BatoFunctionPrefRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BatoFunctionPrefPK)}�����{@@link #forRow(BatoFunctionPrefRow)}���g�p���Ă��������B 
   */
    public static BatoFunctionPrefDao findDaoByPk( BatoFunctionPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoFunctionPrefRow row = findRowByPk( pk );
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
   * p_functionDiv, and �ɂĎw��̒l�����ӂ�{@@link BatoFunctionPrefRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_functionDiv �����Ώۂł���p_functionDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_functionDiv, and �̒l�ƈ�v����{@@link BatoFunctionPrefRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BatoFunctionPrefRow findRowByFunctionDiv( String p_functionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatoFunctionPrefRow.TYPE,
            "function_div=?",
            null,
            new Object[] { p_functionDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatoFunctionPrefRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatoFunctionPrefDao.findRowsByFunctionDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByFunctionDiv(String)}�����{@@link #forRow(BatoFunctionPrefRow)}���g�p���Ă��������B 
   */
    public static BatoFunctionPrefDao findDaoByFunctionDiv( String p_functionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFunctionDiv( p_functionDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
