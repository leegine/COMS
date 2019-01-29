head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	RequestCodesForReadDao.java;


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
 * {@@link RequestCodesForReadDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RequestCodesForReadRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RequestCodesForReadPK 
 * @@see RequestCodesForReadRow 
 */
public class RequestCodesForReadDao extends DataAccessObject {


  /** 
   * ����{@@link RequestCodesForReadDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RequestCodesForReadRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RequestCodesForReadRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RequestCodesForReadDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RequestCodesForReadDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RequestCodesForReadRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RequestCodesForReadRow )
                return new RequestCodesForReadDao( (RequestCodesForReadRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RequestCodesForReadRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RequestCodesForReadRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RequestCodesForReadRow}�I�u�W�F�N�g 
    */
    protected RequestCodesForReadDao( RequestCodesForReadRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RequestCodesForReadRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RequestCodesForReadRow getRequestCodesForReadRow() {
        return row;
    }


  /** 
   * �w���{@@link RequestCodesForReadRow}�I�u�W�F�N�g����{@@link RequestCodesForReadDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RequestCodesForReadRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RequestCodesForReadDao}�擾�̂��߂Ɏw���{@@link RequestCodesForReadRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RequestCodesForReadDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RequestCodesForReadDao forRow( RequestCodesForReadRow row ) throws java.lang.IllegalArgumentException {
        return (RequestCodesForReadDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RequestCodesForReadRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link RequestCodesForReadRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link RequestCodesForReadPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link RequestCodesForReadParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RequestCodesForReadRow.TYPE );
    }


  /** 
   * {@@link RequestCodesForReadRow}����ӂɓ��肷��{@@link RequestCodesForReadPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link RequestCodesForReadRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link RequestCodesForReadParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link RequestCodesForReadPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static RequestCodesForReadPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link RequestCodesForReadRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_ptype �����Ώۂł���p_ptype�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RequestCodesForReadRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RequestCodesForReadRow findRowByPk( String p_requestCode, String p_ptype ) throws DataFindException, DataQueryException, DataNetworkException {
        RequestCodesForReadPK pk = new RequestCodesForReadPK( p_requestCode, p_ptype );
        return findRowByPk( pk );
    }


  /** 
   * �w���RequestCodesForReadPK�I�u�W�F�N�g����{@@link RequestCodesForReadRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����RequestCodesForReadPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link RequestCodesForReadRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static RequestCodesForReadRow findRowByPk( RequestCodesForReadPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RequestCodesForReadRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(RequestCodesForReadRow)}���g�p���Ă��������B 
   */
    public static RequestCodesForReadDao findDaoByPk( String p_requestCode, String p_ptype ) throws DataFindException, DataQueryException, DataNetworkException {
        RequestCodesForReadPK pk = new RequestCodesForReadPK( p_requestCode, p_ptype );
        RequestCodesForReadRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(RequestCodesForReadPK)}�����{@@link #forRow(RequestCodesForReadRow)}���g�p���Ă��������B 
   */
    public static RequestCodesForReadDao findDaoByPk( RequestCodesForReadPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RequestCodesForReadRow row = findRowByPk( pk );
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
   * p_requestCode, p_ptype, and �ɂĎw��̒l�����ӂ�{@@link RequestCodesForReadRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_ptype �����Ώۂł���p_ptype�t�B�[���h�̒l
   * 
   * @@return �����w���p_requestCode, p_ptype, and �̒l�ƈ�v����{@@link RequestCodesForReadRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static RequestCodesForReadRow findRowByRequestCodePtype( String p_requestCode, String p_ptype ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RequestCodesForReadRow.TYPE,
            "request_code=? and ptype=?",
            null,
            new Object[] { p_requestCode, p_ptype } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RequestCodesForReadRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RequestCodesForReadDao.findRowsByRequestCodePtype(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByRequestCodePtype(String, String)}�����{@@link #forRow(RequestCodesForReadRow)}���g�p���Ă��������B 
   */
    public static RequestCodesForReadDao findDaoByRequestCodePtype( String p_requestCode, String p_ptype ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByRequestCodePtype( p_requestCode, p_ptype ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
