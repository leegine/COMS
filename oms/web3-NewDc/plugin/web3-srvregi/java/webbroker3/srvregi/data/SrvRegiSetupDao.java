head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.43.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiSetupDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.srvregi.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SrvRegiSetupDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SrvRegiSetupRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SrvRegiSetupPK 
 * @@see SrvRegiSetupRow 
 */
public class SrvRegiSetupDao extends DataAccessObject {


  /** 
   * ����{@@link SrvRegiSetupDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SrvRegiSetupRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SrvRegiSetupRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SrvRegiSetupDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SrvRegiSetupDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SrvRegiSetupRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiSetupRow )
                return new SrvRegiSetupDao( (SrvRegiSetupRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiSetupRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiSetupRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SrvRegiSetupRow}�I�u�W�F�N�g 
    */
    protected SrvRegiSetupDao( SrvRegiSetupRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SrvRegiSetupRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SrvRegiSetupRow getSrvRegiSetupRow() {
        return row;
    }


  /** 
   * �w���{@@link SrvRegiSetupRow}�I�u�W�F�N�g����{@@link SrvRegiSetupDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SrvRegiSetupRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SrvRegiSetupDao}�擾�̂��߂Ɏw���{@@link SrvRegiSetupRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SrvRegiSetupDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SrvRegiSetupDao forRow( SrvRegiSetupRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiSetupDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiSetupRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SrvRegiSetupRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SrvRegiSetupPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SrvRegiSetupParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiSetupRow.TYPE );
    }


  /** 
   * {@@link SrvRegiSetupRow}����ӂɓ��肷��{@@link SrvRegiSetupPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SrvRegiSetupRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SrvRegiSetupParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SrvRegiSetupPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SrvRegiSetupPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SrvRegiSetupRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiSetupRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiSetupRow findRowByPk( String p_institutionCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiSetupPK pk = new SrvRegiSetupPK( p_institutionCode, p_srvDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���SrvRegiSetupPK�I�u�W�F�N�g����{@@link SrvRegiSetupRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SrvRegiSetupPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiSetupRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiSetupRow findRowByPk( SrvRegiSetupPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiSetupRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(SrvRegiSetupRow)}���g�p���Ă��������B 
   */
    public static SrvRegiSetupDao findDaoByPk( String p_institutionCode, String p_srvDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiSetupPK pk = new SrvRegiSetupPK( p_institutionCode, p_srvDiv );
        SrvRegiSetupRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SrvRegiSetupPK)}�����{@@link #forRow(SrvRegiSetupRow)}���g�p���Ă��������B 
   */
    public static SrvRegiSetupDao findDaoByPk( SrvRegiSetupPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiSetupRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, and �ɂĎw��̒l�����ӂ�{@@link SrvRegiSetupRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_srvDiv, and �̒l�ƈ�v����{@@link SrvRegiSetupRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SrvRegiSetupRow findRowByInstitutionCodeSrvDiv( String p_institutionCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiSetupRow.TYPE,
            "institution_code=? and srv_div=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiSetupRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiSetupDao.findRowsByInstitutionCodeSrvDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeSrvDiv(String, String)}�����{@@link #forRow(SrvRegiSetupRow)}���g�p���Ă��������B 
   */
    public static SrvRegiSetupDao findDaoByInstitutionCodeSrvDiv( String p_institutionCode, String p_srvDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDiv( p_institutionCode, p_srvDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
