head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenRequestNumberDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AccOpenRequestNumberDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AccOpenRequestNumberRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AccOpenRequestNumberPK 
 * @@see AccOpenRequestNumberRow 
 */
public class AccOpenRequestNumberDao extends DataAccessObject {


  /** 
   * ����{@@link AccOpenRequestNumberDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AccOpenRequestNumberRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AccOpenRequestNumberRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AccOpenRequestNumberDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AccOpenRequestNumberDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AccOpenRequestNumberRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenRequestNumberRow )
                return new AccOpenRequestNumberDao( (AccOpenRequestNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenRequestNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenRequestNumberRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g 
    */
    protected AccOpenRequestNumberDao( AccOpenRequestNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AccOpenRequestNumberRow getAccOpenRequestNumberRow() {
        return row;
    }


  /** 
   * �w���{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g����{@@link AccOpenRequestNumberDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AccOpenRequestNumberRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AccOpenRequestNumberDao}�擾�̂��߂Ɏw���{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AccOpenRequestNumberDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AccOpenRequestNumberDao forRow( AccOpenRequestNumberRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenRequestNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenRequestNumberRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AccOpenRequestNumberRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AccOpenRequestNumberPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AccOpenRequestNumberParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenRequestNumberRow.TYPE );
    }


  /** 
   * {@@link AccOpenRequestNumberRow}����ӂɓ��肷��{@@link AccOpenRequestNumberPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AccOpenRequestNumberRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AccOpenRequestNumberParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AccOpenRequestNumberPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AccOpenRequestNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenRequestNumberRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenRequestNumberRow findRowByPk( String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenRequestNumberPK pk = new AccOpenRequestNumberPK( p_institutionCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���AccOpenRequestNumberPK�I�u�W�F�N�g����{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AccOpenRequestNumberPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AccOpenRequestNumberRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AccOpenRequestNumberRow findRowByPk( AccOpenRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenRequestNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(AccOpenRequestNumberRow)}���g�p���Ă��������B 
   */
    public static AccOpenRequestNumberDao findDaoByPk( String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenRequestNumberPK pk = new AccOpenRequestNumberPK( p_institutionCode );
        AccOpenRequestNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AccOpenRequestNumberPK)}�����{@@link #forRow(AccOpenRequestNumberRow)}���g�p���Ă��������B 
   */
    public static AccOpenRequestNumberDao findDaoByPk( AccOpenRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenRequestNumberRow row = findRowByPk( pk );
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
   * p_institutionCode, and �ɂĎw��̒l�����ӂ�{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, and �̒l�ƈ�v����{@@link AccOpenRequestNumberRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AccOpenRequestNumberRow findRowByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenRequestNumberRow.TYPE,
            "institution_code=?",
            null,
            new Object[] { p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenRequestNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenRequestNumberDao.findRowsByInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCode(String)}�����{@@link #forRow(AccOpenRequestNumberRow)}���g�p���Ă��������B 
   */
    public static AccOpenRequestNumberDao findDaoByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCode( p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@