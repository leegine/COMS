head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.59.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlRequestNumberDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.inform.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link InformCtrlRequestNumberDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link InformCtrlRequestNumberRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see InformCtrlRequestNumberPK 
 * @@see InformCtrlRequestNumberRow 
 */
public class InformCtrlRequestNumberDao extends DataAccessObject {


  /** 
   * ����{@@link InformCtrlRequestNumberDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private InformCtrlRequestNumberRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link InformCtrlRequestNumberRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link InformCtrlRequestNumberDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link InformCtrlRequestNumberDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link InformCtrlRequestNumberRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformCtrlRequestNumberRow )
                return new InformCtrlRequestNumberDao( (InformCtrlRequestNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformCtrlRequestNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformCtrlRequestNumberRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g 
    */
    protected InformCtrlRequestNumberDao( InformCtrlRequestNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g���擾���܂��B
   */
    public InformCtrlRequestNumberRow getInformCtrlRequestNumberRow() {
        return row;
    }


  /** 
   * �w���{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g����{@@link InformCtrlRequestNumberDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link InformCtrlRequestNumberRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link InformCtrlRequestNumberDao}�擾�̂��߂Ɏw���{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link InformCtrlRequestNumberDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static InformCtrlRequestNumberDao forRow( InformCtrlRequestNumberRow row ) throws java.lang.IllegalArgumentException {
        return (InformCtrlRequestNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformCtrlRequestNumberRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link InformCtrlRequestNumberRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link InformCtrlRequestNumberPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link InformCtrlRequestNumberParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformCtrlRequestNumberRow.TYPE );
    }


  /** 
   * {@@link InformCtrlRequestNumberRow}����ӂɓ��肷��{@@link InformCtrlRequestNumberPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link InformCtrlRequestNumberRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link InformCtrlRequestNumberParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link InformCtrlRequestNumberPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static InformCtrlRequestNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformCtrlRequestNumberRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformCtrlRequestNumberRow findRowByPk( String p_institutionCode, String p_informDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlRequestNumberPK pk = new InformCtrlRequestNumberPK( p_institutionCode, p_informDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���InformCtrlRequestNumberPK�I�u�W�F�N�g����{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����InformCtrlRequestNumberPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InformCtrlRequestNumberRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InformCtrlRequestNumberRow findRowByPk( InformCtrlRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformCtrlRequestNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(InformCtrlRequestNumberRow)}���g�p���Ă��������B 
   */
    public static InformCtrlRequestNumberDao findDaoByPk( String p_institutionCode, String p_informDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlRequestNumberPK pk = new InformCtrlRequestNumberPK( p_institutionCode, p_informDiv );
        InformCtrlRequestNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(InformCtrlRequestNumberPK)}�����{@@link #forRow(InformCtrlRequestNumberRow)}���g�p���Ă��������B 
   */
    public static InformCtrlRequestNumberDao findDaoByPk( InformCtrlRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlRequestNumberRow row = findRowByPk( pk );
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
   * p_institutionCode, p_informDiv, and �ɂĎw��̒l�����ӂ�{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_informDiv �����Ώۂł���p_informDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_informDiv, and �̒l�ƈ�v����{@@link InformCtrlRequestNumberRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static InformCtrlRequestNumberRow findRowByInstitutionCodeInformDiv( String p_institutionCode, String p_informDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformCtrlRequestNumberRow.TYPE,
            "institution_code=? and inform_div=?",
            null,
            new Object[] { p_institutionCode, p_informDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformCtrlRequestNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformCtrlRequestNumberDao.findRowsByInstitutionCodeInformDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeInformDiv(String, String)}�����{@@link #forRow(InformCtrlRequestNumberRow)}���g�p���Ă��������B 
   */
    public static InformCtrlRequestNumberDao findDaoByInstitutionCodeInformDiv( String p_institutionCode, String p_informDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeInformDiv( p_institutionCode, p_informDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
