head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadTempDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AdministratorUploadTempDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AdministratorUploadTempRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AdministratorUploadTempPK 
 * @@see AdministratorUploadTempRow 
 */
public class AdministratorUploadTempDao extends DataAccessObject {


  /** 
   * ����{@@link AdministratorUploadTempDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AdministratorUploadTempRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AdministratorUploadTempRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AdministratorUploadTempDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AdministratorUploadTempDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AdministratorUploadTempRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorUploadTempRow )
                return new AdministratorUploadTempDao( (AdministratorUploadTempRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorUploadTempRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorUploadTempRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AdministratorUploadTempRow}�I�u�W�F�N�g 
    */
    protected AdministratorUploadTempDao( AdministratorUploadTempRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AdministratorUploadTempRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AdministratorUploadTempRow getAdministratorUploadTempRow() {
        return row;
    }


  /** 
   * �w���{@@link AdministratorUploadTempRow}�I�u�W�F�N�g����{@@link AdministratorUploadTempDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AdministratorUploadTempRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AdministratorUploadTempDao}�擾�̂��߂Ɏw���{@@link AdministratorUploadTempRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AdministratorUploadTempDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AdministratorUploadTempDao forRow( AdministratorUploadTempRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorUploadTempDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorUploadTempRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AdministratorUploadTempRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AdministratorUploadTempPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AdministratorUploadTempParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorUploadTempRow.TYPE );
    }


  /** 
   * {@@link AdministratorUploadTempRow}����ӂɓ��肷��{@@link AdministratorUploadTempPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AdministratorUploadTempRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AdministratorUploadTempParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AdministratorUploadTempPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AdministratorUploadTempPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AdministratorUploadTempRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_administratorUploadId �����Ώۂł���p_administratorUploadId�t�B�[���h�̒l
   * @@param p_lineNumber �����Ώۂł���p_lineNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorUploadTempRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorUploadTempRow findRowByPk( long p_administratorUploadId, int p_lineNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadTempPK pk = new AdministratorUploadTempPK( p_administratorUploadId, p_lineNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���AdministratorUploadTempPK�I�u�W�F�N�g����{@@link AdministratorUploadTempRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AdministratorUploadTempPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorUploadTempRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorUploadTempRow findRowByPk( AdministratorUploadTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorUploadTempRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,int)}�����{@@link #forRow(AdministratorUploadTempRow)}���g�p���Ă��������B 
   */
    public static AdministratorUploadTempDao findDaoByPk( long p_administratorUploadId, int p_lineNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadTempPK pk = new AdministratorUploadTempPK( p_administratorUploadId, p_lineNumber );
        AdministratorUploadTempRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AdministratorUploadTempPK)}�����{@@link #forRow(AdministratorUploadTempRow)}���g�p���Ă��������B 
   */
    public static AdministratorUploadTempDao findDaoByPk( AdministratorUploadTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadTempRow row = findRowByPk( pk );
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
   * p_administratorUploadId, p_lineNumber, and �ɂĎw��̒l�����ӂ�{@@link AdministratorUploadTempRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_administratorUploadId �����Ώۂł���p_administratorUploadId�t�B�[���h�̒l
   * @@param p_lineNumber �����Ώۂł���p_lineNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_administratorUploadId, p_lineNumber, and �̒l�ƈ�v����{@@link AdministratorUploadTempRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdministratorUploadTempRow findRowByAdministratorUploadIdLineNumber( long p_administratorUploadId, int p_lineNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorUploadTempRow.TYPE,
            "administrator_upload_id=? and line_number=?",
            null,
            new Object[] { new Long(p_administratorUploadId), new Integer(p_lineNumber) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorUploadTempRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorUploadTempDao.findRowsByAdministratorUploadIdLineNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAdministratorUploadIdLineNumber(long, int)}�����{@@link #forRow(AdministratorUploadTempRow)}���g�p���Ă��������B 
   */
    public static AdministratorUploadTempDao findDaoByAdministratorUploadIdLineNumber( long p_administratorUploadId, int p_lineNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAdministratorUploadIdLineNumber( p_administratorUploadId, p_lineNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
