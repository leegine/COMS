head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiLotInfoDao.java;


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
 * {@@link SrvRegiLotInfoDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SrvRegiLotInfoRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SrvRegiLotInfoPK 
 * @@see SrvRegiLotInfoRow 
 */
public class SrvRegiLotInfoDao extends DataAccessObject {


  /** 
   * ����{@@link SrvRegiLotInfoDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SrvRegiLotInfoRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SrvRegiLotInfoRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SrvRegiLotInfoDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SrvRegiLotInfoDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SrvRegiLotInfoRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiLotInfoRow )
                return new SrvRegiLotInfoDao( (SrvRegiLotInfoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiLotInfoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiLotInfoRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g 
    */
    protected SrvRegiLotInfoDao( SrvRegiLotInfoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SrvRegiLotInfoRow getSrvRegiLotInfoRow() {
        return row;
    }


  /** 
   * �w���{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g����{@@link SrvRegiLotInfoDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SrvRegiLotInfoRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SrvRegiLotInfoDao}�擾�̂��߂Ɏw���{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SrvRegiLotInfoDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SrvRegiLotInfoDao forRow( SrvRegiLotInfoRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiLotInfoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiLotInfoRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SrvRegiLotInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SrvRegiLotInfoPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SrvRegiLotInfoParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiLotInfoRow.TYPE );
    }


  /** 
   * {@@link SrvRegiLotInfoRow}����ӂɓ��肷��{@@link SrvRegiLotInfoPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SrvRegiLotInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SrvRegiLotInfoParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SrvRegiLotInfoPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SrvRegiLotInfoPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * @@param p_consecutiveNumbers �����Ώۂł���p_consecutiveNumbers�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiLotInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiLotInfoRow findRowByPk( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiLotInfoPK pk = new SrvRegiLotInfoPK( p_institutionCode, p_srvDiv, p_consecutiveNumbers );
        return findRowByPk( pk );
    }


  /** 
   * �w���SrvRegiLotInfoPK�I�u�W�F�N�g����{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SrvRegiLotInfoPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SrvRegiLotInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SrvRegiLotInfoRow findRowByPk( SrvRegiLotInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiLotInfoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,long)}�����{@@link #forRow(SrvRegiLotInfoRow)}���g�p���Ă��������B 
   */
    public static SrvRegiLotInfoDao findDaoByPk( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiLotInfoPK pk = new SrvRegiLotInfoPK( p_institutionCode, p_srvDiv, p_consecutiveNumbers );
        SrvRegiLotInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SrvRegiLotInfoPK)}�����{@@link #forRow(SrvRegiLotInfoRow)}���g�p���Ă��������B 
   */
    public static SrvRegiLotInfoDao findDaoByPk( SrvRegiLotInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiLotInfoRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, p_consecutiveNumbers, and �ɂĎw��̒l�����ӂ�{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_srvDiv �����Ώۂł���p_srvDiv�t�B�[���h�̒l
   * @@param p_consecutiveNumbers �����Ώۂł���p_consecutiveNumbers�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_srvDiv, p_consecutiveNumbers, and �̒l�ƈ�v����{@@link SrvRegiLotInfoRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SrvRegiLotInfoRow findRowByInstitutionCodeSrvDivConsecutiveNumbers( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiLotInfoRow.TYPE,
            "institution_code=? and srv_div=? and consecutive_numbers=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv, new Long(p_consecutiveNumbers) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiLotInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiLotInfoDao.findRowsByInstitutionCodeSrvDivConsecutiveNumbers(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeSrvDivConsecutiveNumbers(String, String, long)}�����{@@link #forRow(SrvRegiLotInfoRow)}���g�p���Ă��������B 
   */
    public static SrvRegiLotInfoDao findDaoByInstitutionCodeSrvDivConsecutiveNumbers( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDivConsecutiveNumbers( p_institutionCode, p_srvDiv, p_consecutiveNumbers ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
