head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerInformationDao.java;


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
 * {@@link VirtualServerInformationDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link VirtualServerInformationRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see VirtualServerInformationPK 
 * @@see VirtualServerInformationRow 
 */
public class VirtualServerInformationDao extends DataAccessObject {


  /** 
   * ����{@@link VirtualServerInformationDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private VirtualServerInformationRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link VirtualServerInformationRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link VirtualServerInformationDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link VirtualServerInformationDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link VirtualServerInformationRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof VirtualServerInformationRow )
                return new VirtualServerInformationDao( (VirtualServerInformationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a VirtualServerInformationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link VirtualServerInformationRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link VirtualServerInformationRow}�I�u�W�F�N�g 
    */
    protected VirtualServerInformationDao( VirtualServerInformationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link VirtualServerInformationRow}�I�u�W�F�N�g���擾���܂��B
   */
    public VirtualServerInformationRow getVirtualServerInformationRow() {
        return row;
    }


  /** 
   * �w���{@@link VirtualServerInformationRow}�I�u�W�F�N�g����{@@link VirtualServerInformationDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link VirtualServerInformationRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link VirtualServerInformationDao}�擾�̂��߂Ɏw���{@@link VirtualServerInformationRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link VirtualServerInformationDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static VirtualServerInformationDao forRow( VirtualServerInformationRow row ) throws java.lang.IllegalArgumentException {
        return (VirtualServerInformationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link VirtualServerInformationRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link VirtualServerInformationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link VirtualServerInformationPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link VirtualServerInformationParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( VirtualServerInformationRow.TYPE );
    }


  /** 
   * {@@link VirtualServerInformationRow}����ӂɓ��肷��{@@link VirtualServerInformationPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link VirtualServerInformationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link VirtualServerInformationParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link VirtualServerInformationPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static VirtualServerInformationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link VirtualServerInformationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_virtualServerNumberJsoes �����Ώۂł���p_virtualServerNumberJsoes�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link VirtualServerInformationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static VirtualServerInformationRow findRowByPk( String p_virtualServerNumberJsoes ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerInformationPK pk = new VirtualServerInformationPK( p_virtualServerNumberJsoes );
        return findRowByPk( pk );
    }


  /** 
   * �w���VirtualServerInformationPK�I�u�W�F�N�g����{@@link VirtualServerInformationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����VirtualServerInformationPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link VirtualServerInformationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static VirtualServerInformationRow findRowByPk( VirtualServerInformationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (VirtualServerInformationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(VirtualServerInformationRow)}���g�p���Ă��������B 
   */
    public static VirtualServerInformationDao findDaoByPk( String p_virtualServerNumberJsoes ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerInformationPK pk = new VirtualServerInformationPK( p_virtualServerNumberJsoes );
        VirtualServerInformationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(VirtualServerInformationPK)}�����{@@link #forRow(VirtualServerInformationRow)}���g�p���Ă��������B 
   */
    public static VirtualServerInformationDao findDaoByPk( VirtualServerInformationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerInformationRow row = findRowByPk( pk );
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


  /** 
   * p_virtualServerNumberJsoes, and �ɂĎw��̒l�Ɉ�v����{@@link VirtualServerInformationRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_virtualServerNumberJsoes �����Ώۂł���p_virtualServerNumberJsoes�t�B�[���h�̒l
   * 
   * @@return �����w���p_virtualServerNumberJsoes, and �̒l�ƈ�v����{@@link VirtualServerInformationRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByVirtualServerNumberJsoes( String p_virtualServerNumberJsoes ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            VirtualServerInformationRow.TYPE,
            "virtual_server_number_jsoes=?",
            null,
            new Object[] { p_virtualServerNumberJsoes } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByVirtualServerNumberJsoes(String)}�����{@@link #forRow(VirtualServerInformationRow)}���g�p���Ă��������B 
   */
    public static List findDaosByVirtualServerNumberJsoes( String p_virtualServerNumberJsoes ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByVirtualServerNumberJsoes( p_virtualServerNumberJsoes ) );
    }

}
@
